package fr.oxodao.overcraft;

import fr.oxodao.overcraft.items.OCItems;
import fr.oxodao.overcraft.stolen.Stolen;
import fr.oxodao.overcraft.utils.CreativeTab;
import fr.oxodao.overcraft.utils.Registration;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLEnvironment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Overcraft.MOD_ID)
public class Overcraft
{
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "overcraft";

    public static final CreativeModeTab tab = new CreativeTab();

    public Overcraft() {
        Registration.init();

        (new OCItems()).init();

        Stolen.init();

        if (FMLEnvironment.dist.isClient()) {
            ClientOvercraft.init();
        }
    }
}
