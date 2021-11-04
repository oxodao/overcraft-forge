package fr.oxodao.overcraft.items;

import fr.oxodao.overcraft.Overcraft;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;

public class OCBlockItem extends BlockItem {
    public OCBlockItem(Block b, String registryName) {
        super(b, (new Properties()).tab(Overcraft.tab));
        this.setRegistryName(registryName);

        OCItems.items.add(this);
    }
}
