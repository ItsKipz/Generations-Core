package generations.gg.generations.core.generationscore.world.level.block.entities;

import generations.gg.generations.core.generationscore.client.model.ModelContextProviders;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import org.joml.Vector3f;

public class StreetLampBlockEntity extends DyedVariantBlockEntity<StreetLampBlockEntity> implements ModelContextProviders.TintProvider {
    public StreetLampBlockEntity(BlockPos arg2, BlockState arg3) {
        super(GenerationsBlockEntities.STREET_LAMP.get(), arg2, arg3);
    }

    @Override
    public Vector3f getTint() {
        return DyedVariantBlockEntity.COLOR_MAP.get(getColor());
    }

    @Override
    public String getVariant() {
        return null;
    }
}
