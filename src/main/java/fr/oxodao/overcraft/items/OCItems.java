package fr.oxodao.overcraft.items;

import fr.oxodao.overcraft.events.PokeballEvents;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.ArrayList;

public class OCItems {
    public static ArrayList<BaseItem> items = new ArrayList<>();

    public static PokeballItem POKEBALL = new PokeballItem();

    public static void registerEvents() {
        MinecraftForge.EVENT_BUS.register(PokeballEvents.class);
    }

    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event) {
        for (BaseItem i : OCItems.items) {
            i.register(event);
        }
    }
}
