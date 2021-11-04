package fr.oxodao.overcraft.tile;

import fr.oxodao.overcraft.utils.Registration;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class RepulsiveTorchEntity extends BlockEntity {

    public RepulsiveTorchEntity(BlockPos bp, BlockState bs) {
        super(Registration.REPULSIVE_TORCH_TILE.get(), bp, bs);
    }

    public static <T extends BlockEntity> void tick(Level w, BlockPos bp, BlockState bs, T be) {
        if (!(be instanceof RepulsiveTorchEntity)) {
            return;
        }

        if (w.isClientSide)
            return;

        int radius = 4;
        List<Entity> entities = w.getEntities(null, AABB.of((new BoundingBox(bp)).inflate(radius)));
        for (Entity entity : entities) {
            if (entity instanceof Player || entity.hasCustomName())
                continue;

            double distance = Math.sqrt(entity.distanceToSqr(bp.getX(), bp.getY(), bp.getZ()));

            if (distance < radius && distance != 0.0D) {
                if (distance < 1.0D)
                    distance = 1.0D;

                double knockbackMultiplier = 1.0D + 1.0D / distance;
                double reductionCoefficient = 0.04D;

                // @TODO check blaze mes couilles la
                if (entity instanceof Projectile) {
                    Projectile pe = (Projectile)entity;
                    if (!(pe.getOwner() instanceof Player)) {
                        reductionCoefficient = 1.5D;
                    }
                }

                Vec3 angleOfAttack = new Vec3(entity.getX() - bp.getX() + 0.5D, entity.getY() - bp.getY(), entity.getZ() - bp.getZ() + 0.5D);

                double xForce = angleOfAttack.x * knockbackMultiplier * reductionCoefficient;
                double yForce = angleOfAttack.y * knockbackMultiplier * reductionCoefficient;
                double zForce = angleOfAttack.z * knockbackMultiplier * reductionCoefficient;

                entity.move(MoverType.SELF, new Vec3(xForce, yForce, zForce));
            }
        }
    }
}
