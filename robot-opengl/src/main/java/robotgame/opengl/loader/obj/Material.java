package robotgame.opengl.loader.obj;

/**
 * @author tomekk
 * @since 2010-11-19, 18:24:43
 */
public class Material {

    private float[] ambient;
    private float[] diffuse;
    private float[] specular;
    private float[] shinness;

    public float[] getAmbient() {
        return ambient;
    }

    public void setAmbient(float[] ambient) {
        this.ambient = ambient;
    }

    public float[] getDiffuse() {
        return diffuse;
    }

    public void setDiffuse(float[] diffuse) {
        this.diffuse = diffuse;
    }

    public float[] getSpecular() {
        return specular;
    }

    public void setSpecular(float[] specular) {
        this.specular = specular;
    }

    public float[] getShinness() {
        return shinness;
    }

    public void setShinness(float[] shinness) {
        this.shinness = shinness;
    }
}
