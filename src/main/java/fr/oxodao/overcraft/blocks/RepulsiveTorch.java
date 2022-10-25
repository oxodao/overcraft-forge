package fr.oxodao.overcraft.blocks;

import fr.oxodao.overcraft.tile.RepulsiveTorchEntity;
import fr.oxodao.overcraft.utils.Registration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

import javax.annotation.Nullable;

public class RepulsiveTorch extends TorchBlock implements EntityBlock {

    public RepulsiveTorch() {
        super(
                BlockBehaviour.Properties
                        .of(Material.DECORATION)
                        .noCollission()
                        .instabreak()
                        .lightLevel(bs -> 14)
                        .sound(SoundType.WOOD),
                ParticleTypes.FLAME
        );
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos bp, BlockState bs) {
        return new RepulsiveTorchEntity(bp, bs);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level w, BlockState bs, BlockEntityType<T> bet) {
        return bet == Registration.REPULSIVE_TORCH_TILE.get() ? RepulsiveTorchEntity::tick : null;
    }
}
