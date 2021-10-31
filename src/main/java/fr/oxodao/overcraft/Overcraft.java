package fr.oxodao.overcraft;

import fr.oxodao.overcraft.blocks.OCBlocks;
import fr.oxodao.overcraft.items.OCItems;
import fr.oxodao.overcraft.utils.CreativeTab;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Overcraft.MOD_ID)
public class Overcraft
{
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "overcraft";

    public static final CreativeModeTab tab = new CreativeTab();

    public Overcraft() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().register(new OCItems());

        OCItems.registerEvents();
    }

    private void setup(final FMLCommonSetupEvent event)
    {

    }
}
