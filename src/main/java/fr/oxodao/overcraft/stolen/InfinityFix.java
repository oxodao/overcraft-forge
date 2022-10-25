package fr.oxodao.overcraft.stolen;

import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.event.entity.player.ArrowNockEvent;

public class InfinityFix {

    void fix(final ArrowNockEvent event) {
        // @TODO: Fix deprecated
        if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, event.getBow()) > 0) {
            event.getEntity().startUsingItem(event.getHand());
            event.setAction(InteractionResultHolder.success(event.getBow()));
        }
    }
}
