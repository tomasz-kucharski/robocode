package robot;

import robot.object.WorldObject;
import robot.object.opengl.ObjectGL;

import javax.media.opengl.GL;
import java.nio.FloatBuffer;

public class LightGL {
    public FloatBuffer LightAmbient = FloatBuffer.allocate(4);
    public FloatBuffer LightDiffuse = FloatBuffer.allocate(4);
    public FloatBuffer LightPosition = FloatBuffer.allocate(4);
    public FloatBuffer LightSpecular = FloatBuffer.allocate(4);
    public FloatBuffer LightShininess = FloatBuffer.allocate(4);

    int filter;

    public LightGL() {
        LightAmbient.put(new float[]{0.4f, 0.4f, 0.4f, 1.0f});
        LightDiffuse.put(new float[]{1.0f, 1.0f, 1.0f, 1.0f});
        LightPosition.put(new float[]{10.0f, 10.0f, -10.0f, 5.0f});
        LightSpecular.put(new float[]{0.8f, 0.8f, 0.8f, 1.0f});
    }

    void init() {

    }
}