package fr.oxodao.overcraft;

import fr.oxodao.overcraft.utils.Registration;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@OnlyIn(Dist.CLIENT)
public class ClientOvercraft {

    public static void init() {
        FMLJavaModLoadingContext.get().getModEventBus().register(ClientOvercraft.class);
    }

    @SubscribeEvent
    public static void onClientSetupEvent(FMLClientSetupEvent e) {
        // @TODO: fix deprecated
        ItemBlockRenderTypes.setRenderLayer(Registration.REPULSIVE_TORCH.get(), RenderType.cutout());
    }

}
