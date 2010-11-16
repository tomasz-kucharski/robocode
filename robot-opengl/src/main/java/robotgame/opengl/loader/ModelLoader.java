package robotgame.opengl.loader;

import com.obj.WavefrontObject;

/**
 * @author tomekk
 * @since 2010-11-06, 12:07:13
 */
public class ModelLoader {

    WavefrontObject loader;

    public ModelLoader(String fileName) {
        loader = new WavefrontObject(fileName);
        System.out.println(loader.getBoudariesText());
    }
}
