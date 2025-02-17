package generations.gg.generations.core.generationscore.world.level.block;

import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.world.level.block.entities.DyedVariantBlockEntity;
import generations.gg.generations.core.generationscore.world.level.block.utilityblocks.DyeableBlock;
import net.minecraft.world.item.DyeColor;

import java.util.Map;

public record DyedGroup<V extends DyeableBlock<T, V>, T extends DyedVariantBlockEntity<?>>(Map<DyeColor, RegistrySupplier<DyeableBlock<T, V>>> block)  {
}
