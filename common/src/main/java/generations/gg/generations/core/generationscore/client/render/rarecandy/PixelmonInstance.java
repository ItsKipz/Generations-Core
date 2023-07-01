package generations.gg.generations.core.generationscore.client.render.rarecandy;

import gg.generations.rarecandy.storage.AnimatedObjectInstance;
import org.joml.Matrix4f;

import java.util.function.Supplier;

public class PixelmonInstance extends AnimatedObjectInstance {

    public final Supplier<LightingSettings> settingsSupplier;
    private Matrix4f projectionMatrix;

    public Matrix4f[] matrixTransforms;

    public PixelmonInstance(Matrix4f transformationMatrix, Matrix4f viewMatrix, String materialId, Supplier<LightingSettings> settingsSupplier) {
        super(transformationMatrix, viewMatrix, materialId);
        this.projectionMatrix = new Matrix4f();
        this.settingsSupplier = settingsSupplier;
    }

    public int lightColor() {
        return settingsSupplier.get().lightColor();
    }

    public float reflectivity() {
        return settingsSupplier.get().reflectivity();
    }

    public float shineDamper() {
        return settingsSupplier.get().shineDamper();
    }

    public float diffuseColorMix() {
        return settingsSupplier.get().diffuseColorMix();
    }
    @Override
    public Matrix4f[] getTransforms() {
        return matrixTransforms;
    }

    public Matrix4f getProjectionMatrix() {
        return projectionMatrix;
    }

    public void setProjectionMatrix(Matrix4f projectionMatrix) {
        this.projectionMatrix.set(projectionMatrix);
    }
}