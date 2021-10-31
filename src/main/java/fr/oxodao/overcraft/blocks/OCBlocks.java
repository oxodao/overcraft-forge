package fr.oxodao.overcraft.blocks;

import fr.oxodao.overcraft.events.BlockListener;
import net.minecraftforge.common.MinecraftForge;

public class OCBlocks {

    public static void init() {
        MinecraftForge.EVENT_BUS.register(new BlockListener());
    }

}
