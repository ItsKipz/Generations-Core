package generations.gg.generations.core.generationscore.world.level.block.shrines;

import com.cobblemon.mod.common.api.pokemon.PokemonProperties;
import dev.architectury.registry.registries.DeferredSupplier;
import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.world.entity.block.PokemonUtil;
import generations.gg.generations.core.generationscore.world.item.MelodyFluteItem;
import generations.gg.generations.core.generationscore.world.item.WingItem;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.world.level.block.entities.generic.GenericShrineBlockEntity;
import generations.gg.generations.core.generationscore.world.level.block.entities.shrines.ShrineBlockEntity;
import generations.gg.generations.core.generationscore.world.level.schedule.ScheduledTask;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SuppressWarnings("deprecation")
public class BirdShrineBlock extends ShrineBlock<GenericShrineBlockEntity> {
    private final Set<ResourceLocation> allowedImbuedItems;

    @SafeVarargs
    public BirdShrineBlock(Properties materialIn, ResourceLocation model, int width, int height, int length, RegistrySupplier<WingItem>... imbuedItems) {
        super(materialIn, GenerationsBlockEntities.GENERIC_SHRINE, model, width, height, length);
        allowedImbuedItems = Stream.of(imbuedItems).filter(Objects::nonNull).map(DeferredSupplier::getKey).map(ResourceKey::location).collect(Collectors.toSet());
    }

    @SafeVarargs
    public BirdShrineBlock(Properties materialIn, ResourceLocation model, RegistrySupplier<WingItem>... imbuedItems) {
        this(materialIn, model, 0, 0, 0, imbuedItems);
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand handIn, @NotNull BlockHitResult hit) {
        if (!level.isClientSide()) {
            var stack = player.getItemInHand(handIn);

            if (MelodyFluteItem.isFlute(stack)) {
                var imbuedStack = MelodyFluteItem.getImbuedItem(stack);
                if (imbuedStack.isEmpty() || allowedImbuedItems.stream().noneMatch(a -> imbuedStack.is(item -> item.is(a)))) return InteractionResult.PASS;
                var pokemonProperties = getProperties(imbuedStack);
                var entity = level.getBlockEntity(hit.getBlockPos());

                if (entity instanceof ShrineBlockEntity shrine && !shrine.isActive() && stack.getDamageValue() >= stack.getMaxDamage() && pokemonProperties != null) {
                    shrine.toggleActive();
                    stack.shrink(1);

                    PokemonUtil.spawn(pokemonProperties, level, entity.getBlockPos());

                    ScheduledTask.schedule(shrine::toggleActive, 150);
                    return InteractionResult.SUCCESS;
                }
            }
        }

        return InteractionResult.PASS;
    }

    @Override
    public String getActiveVariant(boolean active) {
        return active ? "activated" : "deactivated";
    }

    public PokemonProperties getProperties(ItemStack stack) {
        return stack.getItem() instanceof WingItem wing ? wing.getKey().createProperties(70) : null;
    }
}
