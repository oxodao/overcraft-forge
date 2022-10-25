package fr.oxodao.overcraft;

import fr.oxodao.overcraft.events.PokeballEvents;
import fr.oxodao.overcraft.stolen.Stolen;
import fr.oxodao.overcraft.utils.CreativeTab;
import fr.oxodao.overcraft.utils.Registration;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLEnvironment;

@Mod(Overcraft.MOD_ID)
public class Overcraft
{
    public static final String MOD_ID = "overcraft";

    public static final CreativeModeTab tab = new CreativeTab();

    public Overcraft() {
        Registration.init();

        MinecraftForge.EVENT_BUS.register(PokeballEvents.class);

        Stolen.init();

        if (FMLEnvironment.dist.isClient()) {
            ClientOvercraft.init();
        }
    }
}
