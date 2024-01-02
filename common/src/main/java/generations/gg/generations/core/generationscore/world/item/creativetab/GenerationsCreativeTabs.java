package generations.gg.generations.core.generationscore.world.item.creativetab;

import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.world.item.GenerationsItems;
import generations.gg.generations.core.generationscore.world.item.tools.GenerationsAxeItem;
import generations.gg.generations.core.generationscore.world.level.block.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

/**
 * Generations Creative Tabs
 * @author J.T. McQuigg
 * @author WaterPicker
 */
public class GenerationsCreativeTabs {

    public static DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(GenerationsCore.MOD_ID, Registries.CREATIVE_MODE_TAB);
    public static RegistrySupplier<CreativeModeTab> RESTORATION = create("restoration", () -> GenerationsItems.POTION);
    //public static CreativeModeTab TMS = create("tms", () -> GenerationsItems.TM_1);
    public static RegistrySupplier<CreativeModeTab> BADGES = create("badges", () -> GenerationsItems.RAINBOW_BADGE);
    public static RegistrySupplier<CreativeModeTab> RIBBONS = create("ribbons", () -> GenerationsItems.COOLNESS_MASTER_RIBBON);
    //public static RegistrySupplier<CreativeModeTab> HELD_ITEMS = create("held_items", () -> GenerationsItems.AMULET_COIN);
    public static RegistrySupplier<CreativeModeTab> PLAYER_ITEMS = create("player_items", () -> GenerationsItems.POKEDEX);
    public static RegistrySupplier<CreativeModeTab> UTILITY = create("utility_items", () -> GenerationsItems.POKEDEX);
    public static RegistrySupplier<CreativeModeTab> NATURAL = create("natural", () -> GenerationsItems.POKEDEX);
    //public static RegistrySupplier<CreativeModeTab> LEGENDARY_ITEMS = create("legendary_items", () -> GenerationsItems.DNA_SPLICERS);

    public static RegistrySupplier<CreativeModeTab> BUILDING_BLOCKS = create("building_blocks", GenerationsBlocks.TEMPLE_BRICK_SET::getBaseBlockSupplier);
    public static RegistrySupplier<CreativeModeTab> COLORED_BLOCKS = create("colored_blocks", GenerationsBlocks.BLUE_POKE_BRICK_SET::getBaseBlockSupplier);
    public static RegistrySupplier<CreativeModeTab> NATURAL_BLOCKS = create("natural_blocks", GenerationsOres.RUBY_ORE_SET::getOreSupplier);
    public static RegistrySupplier<CreativeModeTab> DECORATION_BLOCKS = create("decoration_blocks", () -> GenerationsBlocks.POKECENTER_SCARLET_SIGN);
    public static RegistrySupplier<CreativeModeTab> FUNCTIONAL_BLOCKS = create("functional_blocks", () -> GenerationsUtilityBlocks.TABLE_PC);

    public static RegistrySupplier<CreativeModeTab> TOOLS_AND_UTILITIES = create("Tools & Utilities", () -> GenerationsItems.SUPER_ROD);
    public static RegistrySupplier<CreativeModeTab> COMBAT = create("Combat", () -> GenerationsItems.CRYSTAL);
    public static RegistrySupplier<CreativeModeTab> INGREDIENTS = create("Ingredients", () -> GenerationsItems.CRYSTAL);
    public static RegistrySupplier<CreativeModeTab> AWARDS = create("Awards", () -> GenerationsItems.RAINBOW_BADGE);
    public static RegistrySupplier<CreativeModeTab> HELD_ITEMS = create("Held Items", () -> GenerationsItems.ABILITY_SHIELD);
    public static RegistrySupplier<CreativeModeTab> CONSUMABLES = create("Consumables", () -> GenerationsItems.ABILITY_CAPSULE);
    public static RegistrySupplier<CreativeModeTab> GIMMICK_ITEMS = create("Gimmick Items", () -> GenerationsItems.VENUSAURITE);
    public static RegistrySupplier<CreativeModeTab> LEGENDARY_ITEMS = create("Legendary Items", () -> GenerationsItems.JEWEL_OF_LIFE);

    public static RegistrySupplier<CreativeModeTab> FORM_ITEMS = create("form_items", () -> GenerationsItems.METEORITE);
    public static RegistrySupplier<CreativeModeTab> POKEMAIL = create("pokemail", () -> GenerationsItems.POKEMAIL_AIR);
    public static RegistrySupplier<CreativeModeTab> VALUABLES = create("valuables", () -> GenerationsItems.STRANGE_SOUVENIR);
    public static RegistrySupplier<CreativeModeTab> CUISINE = create("cuisine", () -> GenerationsItems.GIGANTAMIX);
    public static RegistrySupplier<CreativeModeTab> UNIMPLEMENTED = create("unimplemented", () -> GenerationsItems.ABILITY_URGE);

    private static <T extends ItemLike> RegistrySupplier<CreativeModeTab> create(String name, @NotNull Supplier<Supplier<T>> item) {
        return CREATIVE_TABS.register(name, () -> CreativeTabRegistry.create(builder -> builder.title(Component.translatable(name + "." + GenerationsCore.MOD_ID)).icon(() -> item.get().get().asItem().getDefaultInstance())));
    }

    public static void init() {
        CREATIVE_TABS.register();
    }
}
