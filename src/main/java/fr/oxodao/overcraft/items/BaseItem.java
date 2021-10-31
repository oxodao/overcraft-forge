package fr.oxodao.overcraft.items;

import fr.oxodao.overcraft.Overcraft;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.RegistryEvent;

public class BaseItem extends Item {

    public BaseItem(String unlocalizedName, Properties prop) {
        super(prop.tab(Overcraft.tab));
        this.setRegistryName(unlocalizedName);
        OCItems.items.add(this);
    }

    public void register(RegistryEvent.Register<Item> registry) {
        registry.getRegistry().register(this);
    }
}
