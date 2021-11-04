package fr.oxodao.overcraft.stolen;

import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.event.entity.player.ArrowNockEvent;

public class InfinityFix {

    void fix(final ArrowNockEvent event) {
        if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, event.getBow()) > 0) {
            event.getPlayer().startUsingItem(event.getHand());
            event.setAction(InteractionResultHolder.success(event.getBow()));
        }
    }
}
