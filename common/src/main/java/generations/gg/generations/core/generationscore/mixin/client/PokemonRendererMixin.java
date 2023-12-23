package generations.gg.generations.core.generationscore.mixin.client;

import com.cobblemon.mod.common.client.render.pokemon.PokemonRenderer;
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.client.render.PixelmonInstanceProvider;
import generations.gg.generations.core.generationscore.client.render.rarecandy.CompiledModel;
import generations.gg.generations.core.generationscore.client.render.rarecandy.ModelRegistry;
import generations.gg.generations.core.generationscore.client.render.rarecandy.PixelmonInstance;
import gg.generations.rarecandy.renderer.loading.ModelLoader;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

@Mixin(PokemonRenderer.class)
public abstract class PokemonRendererMixin extends MobRenderer<PokemonEntity, EntityModel<PokemonEntity>> {

    private static final HashMap<ResourceLocation, CompiledModel> MODEL_MAP = new HashMap<>();

    private PokemonRendererMixin(EntityRendererProvider.Context context, EntityModel<PokemonEntity> model, float shadowRadius) {
        super(context, model, shadowRadius);
    }

    @Override
    public void render(PokemonEntity entity, float entityYaw, float partialTicks, PoseStack stack, MultiBufferSource buffer, int packedLight) {
        // Fuck
        MODEL_MAP.computeIfAbsent(GenerationsCore.id("wooper"), resourceLocation -> {
            try {
                return ModelRegistry.LOADER.get(new ModelRegistry.Pair<>(GenerationsCore.id("bedrock/pokemon/models/wooper_paldean.pk"), "pokemon"));
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        });

        var model = MODEL_MAP.get(GenerationsCore.id("wooper"));
        ModelRegistry.getWorldRareCandy().objectManager.add(model.renderObject, ((PixelmonInstanceProvider) (Object) entity).getInstance());
    }
}
