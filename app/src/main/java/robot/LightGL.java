package robot;

import robot.object.WorldObject;
import robot.object.opengl.ObjectGL;

import javax.media.opengl.GL;
import java.nio.FloatBuffer;

public class LightGL {
    public static FloatBuffer LightAmbient = FloatBuffer.wrap(new float[]{0.5f, 0.5f, 0.5f, 1.0f});
    public static FloatBuffer LightDiffuse = FloatBuffer.wrap(new float[]{1f, 1f, 1f, 1f});
    public static FloatBuffer LightPosition = FloatBuffer.wrap(new float[]{0f, 0f, 2f, 1f});
    public static FloatBuffer LightShininess = FloatBuffer.wrap(new float[]{0.5f, 0.5f, 0.5f, 1.0f});
}