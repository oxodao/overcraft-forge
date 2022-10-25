package fr.oxodao.overcraft.events;

import fr.oxodao.overcraft.utils.Registration;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class PokeballEvents {

    @SubscribeEvent
    public static void entityInteract(PlayerInteractEvent.EntityInteract e) {
        var is = e.getItemStack();
        var w = e.getLevel();
        var target = e.getTarget();

        if (w.isClientSide || is.getItem() != Registration.POKEBALL_ITEM.get()) {
            return;
        }

        if (!(target instanceof LivingEntity)) {
            return;
        }

        if (target instanceof Player || target instanceof EnderDragon || target instanceof WitherBoss){
            return;
        }

        CompoundTag nbt = new CompoundTag();
        if (is.hasTag()) {
            nbt = is.getTag();
        }

        if (!nbt.contains("entity")) {
            nbt.putString("entity", EntityType.getKey(target.getType()).toString());
            nbt.putString("mod", EntityType.getKey(target.getType()).getNamespace());

            if (target.hasCustomName() && !target.getCustomName().getString().equalsIgnoreCase(target.getDisplayName().toString())) {
                nbt.putString("nametag", target.getCustomName().getString());
            }

            var tag = PokeballEvents.fillCompoundTag(target);
            nbt.put("captured_entity", tag);

            is.setTag(nbt);
            e.getEntity().setItemInHand(e.getHand(), is);
            target.remove(Entity.RemovalReason.DISCARDED);

            if (target instanceof Animal) {
                ((Animal) target).playAmbientSound();
            }
        }
    }

    private static final String[] ignoredTags = new String[] {
            "Pos", "Motion", "Rotation", "FallDistance",
            "OnGround", "Air", "Fire", "PortalCooldown",
            "HasVisualFire", "Passengers"
    };

    public static CompoundTag fillCompoundTag(Entity target) {
        CompoundTag tag = new CompoundTag();
        target.save(tag);

        for (String k: ignoredTags) {
            tag = PokeballEvents.remove(tag, k);
        }

        return tag;
    }

    private static CompoundTag remove(CompoundTag ct, String k) {
        if (ct.contains(k)) {
            ct.remove(k);
        }

        return ct;
    }
}
