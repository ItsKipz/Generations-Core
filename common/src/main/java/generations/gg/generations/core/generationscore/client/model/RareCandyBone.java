package generations.gg.generations.core.generationscore.client.model;

import com.cobblemon.mod.common.api.pokemon.PokemonSpecies;
import com.cobblemon.mod.common.client.render.models.blockbench.PoseableEntityModel;
import com.cobblemon.mod.common.client.render.models.blockbench.PoseableEntityState;
import com.cobblemon.mod.common.client.render.models.blockbench.pose.Bone;
import com.cobblemon.mod.common.client.render.models.blockbench.repository.PokemonModelRepository;
import com.cobblemon.mod.common.client.render.models.blockbench.repository.VaryingModelRepository;
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import com.cobblemon.mod.common.net.messages.client.data.SpeciesRegistrySyncPacket;
import com.cobblemon.mod.common.pokemon.Species;
import com.mojang.authlib.minecraft.client.MinecraftClient;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferUploader;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import generations.gg.generations.core.generationscore.client.render.PixelmonInstanceProvider;
import generations.gg.generations.core.generationscore.client.render.rarecandy.CompiledModel;
import generations.gg.generations.core.generationscore.client.render.rarecandy.MinecraftClientGameProvider;
import generations.gg.generations.core.generationscore.client.render.rarecandy.ModelRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.NotNull;
import org.joml.Quaternionf;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

public class RareCandyBone implements Supplier<Bone>, Bone {
    private static final Quaternionf ROTATION_CORRECTION = Axis.YP.rotationDegrees(180);
    private final Supplier<CompiledModel> objectSupplier;

    private static final Map<String, Bone> DUMMY = Collections.emptyMap();

    public RareCandyBone(ResourceLocation location) {
        ResourceLocation location1 = location.withPrefix("bedrock/pokemon/models/");
        objectSupplier = () -> ModelRegistry.get(location, "pixelmon");
    }

    @Override
    public <T extends Entity> void renderGui(PoseableEntityModel<T> model, VaryingModelRepository<T, PoseableEntityModel<T>> modelRepository, PoseStack stack, PoseableEntityState<T> state, ResourceLocation species, Set<String> aspects, int packedLight) {
        var compiledModel = objectSupplier.get();

        var instance = ModelRegistry.getInstance();


        var scale = compiledModel.renderObject.scale;
        if(instance != null) {
            var formData = PokemonSpecies.INSTANCE.getByIdentifier(species);

//                scale *= formData.getForm(aspects).getHitbox().height; //TODO: Turn to actual height eventually.

                if(compiledModel.renderObject.isReady()) {
                    var id = modelRepository.getVariations().get(formData.getResourceIdentifier()).getResolvedTexture(aspects, 0F);
                    if(id.getNamespace().equals("pk")) instance.setVariant(id.getPath());
                }

            }

            stack.pushPose();
//            stack.mulPose(ROTATION_CORRECTION);

            stack.scale(scale, scale, scale);
            instance.viewMatrix().set(stack.last().pose());
            stack.popPose();
            compiledModel.render(instance, RenderSystem.getProjectionMatrix());

        RenderSystem.enableDepthTest();
        BufferUploader.reset();
        ModelRegistry.getRareCandy().render(true, MinecraftClientGameProvider.getTimePassed());
        ModelRegistry.freePool();
    }

    @Override
    public Map<String, Bone> getChildren() {
        return DUMMY;
    }

    @Override
    public <T extends Entity> void render(T entity, PoseStack stack, VertexConsumer buffer, int packedLight, int packedOverlay, float r, float g, float b, float a) {
        var model = objectSupplier.get();

        var instance = entity != null ? ((PixelmonInstanceProvider) entity).getInstance() : null;

        if(instance != null) {
            var scale = model.renderObject.scale;

            if(entity instanceof PokemonEntity pokemon) {

                scale *= pokemon.getPokemon().getSpecies().getHitbox().height; //TODO: Turn to actual height eventually.

                if(model.renderObject.isReady()) {
                    var id = PokemonModelRepository.INSTANCE.getVariations().get(pokemon.getPokemon().getSpecies().getResourceIdentifier()).getResolvedTexture(pokemon.getPokemon().getAspects(), 0F);
                    if(id.getNamespace().equals("pk")) instance.setVariant(id.getPath());
                }

            }

            stack.pushPose();
            stack.mulPose(ROTATION_CORRECTION);

            if(entity != null) {
                stack.scale(-1, -1, 1);
                stack.translate(0, -1.501, 0);
            }

            stack.scale(scale, scale, scale);
            instance.viewMatrix().set(stack.last().pose());
            stack.popPose();
            model.render(instance, RenderSystem.getProjectionMatrix());
        }
    }

    @Override
    public void transform(PoseStack poseStack) {

    }

    @NotNull
    @Override
    public Bone get() {
        return this;
    }
}