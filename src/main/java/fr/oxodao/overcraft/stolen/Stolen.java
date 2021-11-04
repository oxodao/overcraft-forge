package fr.oxodao.overcraft.stolen;

import net.minecraftforge.common.MinecraftForge;

public class Stolen {

    /**
     * Things I stole from other small mods
     * This is done so that I don't add weight for lower end PC by loading multiple mod
     * when only one could work
     */

    public static void init() {
        MinecraftForge.EVENT_BUS.addListener((new InfinityFix())::fix);
    }

}
