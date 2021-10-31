package fr.oxodao.overcraft.items;

import net.minecraft.ChatFormatting;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.apache.logging.log4j.core.jmx.Server;

import java.util.List;
import java.util.Optional;

public class PokeballItem extends BaseItem {

    public PokeballItem() {
        super("pokeball", (new Properties()).fireResistant().stacksTo(1));
    }

    @Override
    public InteractionResult useOn(UseOnContext ctx) {
        var ep = ctx.getPlayer();
        var is = ctx.getItemInHand();
        var w = ctx.getLevel();

        if (w.isClientSide || !is.hasTag()) {
            return InteractionResult.SUCCESS;
        }

        CompoundTag nbt = is.getTag();
        if (nbt.contains("entity")) {
            Optional<EntityType<?>> et = EntityType.byString(nbt.getString("entity"));

            if (et.isPresent()) {
                LivingEntity le = (LivingEntity) ((EntityType) et.get()).create(
                        (ServerLevel) w,
                        (CompoundTag) nbt.get("captured_entity"),
                        null,
                        null,
                        new BlockPos(ctx.getClickedPos().above()),
                        MobSpawnType.SPAWN_EGG,
                        true,
                        false
                );

                le.readAdditionalSaveData((CompoundTag) nbt.get("captured_entity"));

                if (nbt.contains("nametag"))
                    le.setCustomName(new TextComponent(nbt.getString("nametag")));

                w.addFreshEntity(le);

                is.setTag(null);
            }
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag context) {
        Style lightGray = Style.EMPTY.withColor(ChatFormatting.GRAY);
        if (stack.hasTag() && stack.getTag().contains("entity")) {
            CompoundTag nbt = stack.getTag();
            Optional<EntityType<?>> et = EntityType.byString(nbt.getString("entity"));
            if (et.isPresent()) {
                String mod = nbt.getString("mod").equals("minecraft") ? "" : (" (" + I18n.get(nbt.getString("mod"), new Object[0]) + ")");

                TextComponent lt = new TextComponent(I18n.get("item.overcraft.pokeball.caught", new Object[0]) + ": " + I18n.get((et.get()).getDescriptionId()) + mod);
                lt.setStyle(lightGray);

                tooltip.add(lt);

                if (nbt.contains("nametag")) {
                    TextComponent ltNametag = new TextComponent(I18n.get("item.overcraft.pokeball.nametag", new Object[0]) + ": " + nbt.getString("nametag"));
                    ltNametag.setStyle(lightGray);

                    tooltip.add(ltNametag);
                }
            }
        } else {
            TextComponent ltEmpty = new TextComponent("Empty");
            ltEmpty.setStyle(lightGray);
            tooltip.add(ltEmpty);
        }
    }

    public boolean isFoil(ItemStack is) {
        return is.hasTag() && is.getTag().contains("entity");
    }

    @Override
    public Rarity getRarity(ItemStack is) {
        if (is.hasTag() && is.getTag().contains("entity"))
            return Rarity.EPIC;

        return Rarity.COMMON;
    }
}
