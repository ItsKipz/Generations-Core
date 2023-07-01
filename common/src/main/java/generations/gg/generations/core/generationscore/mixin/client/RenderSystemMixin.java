package generations.gg.generations.core.generationscore.mixin.client;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(RenderSystem.class)
public class RenderSystemMixin {

    @Overwrite
    public static void enableScissor(int i, int j, int k, int l) {

    }

    @Overwrite
    public static void disableScissor() {
    }

}
