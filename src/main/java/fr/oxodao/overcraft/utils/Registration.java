package fr.oxodao.overcraft.utils;

import fr.oxodao.overcraft.Overcraft;
import fr.oxodao.overcraft.blocks.RepulsiveTorch;
import fr.oxodao.overcraft.tile.RepulsiveTorchEntity;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Registration {
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Overcraft.MOD_ID);
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Overcraft.MOD_ID);
    private static final DeferredRegister<BlockEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, Overcraft.MOD_ID);

    public static final RegistryObject<RepulsiveTorch> REPULSIVE_TORCH = BLOCKS.register("repulsive_torch", RepulsiveTorch::new);
    public static final RegistryObject<BlockEntityType<RepulsiveTorchEntity>> REPULSIVE_TORCH_TILE = TILES.register("repulsive_torch", () -> BlockEntityType.Builder.of(RepulsiveTorchEntity::new, REPULSIVE_TORCH.get()).build(null));

    public static final RegistryObject<Item> REPULSIVE_TORCH_ITEM = ITEMS.register("repulsive_torch", () -> new BlockItem(REPULSIVE_TORCH.get(), new Item.Properties().tab(Overcraft.tab)));

    public static void init() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        TILES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
