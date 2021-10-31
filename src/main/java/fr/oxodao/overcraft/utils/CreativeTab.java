package fr.oxodao.overcraft.utils;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class CreativeTab extends CreativeModeTab {

    public CreativeTab() {
        super("Overcraft");
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(Items.DIAMOND, 1);
    }

}
