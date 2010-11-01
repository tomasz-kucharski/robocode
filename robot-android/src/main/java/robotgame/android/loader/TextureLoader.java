package robotgame.android.loader;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;
import robotgame.android.R;

import javax.imageio.ImageIO;
import javax.microedition.khronos.opengles.GL;
import javax.microedition.khronos.opengles.GL10;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.channels.FileChannel;

/**
 * @author tomekk
 * @since 2010-10-20, 18:01:51
 */
public class TextureLoader extends robotgame.loader.TextureLoader {

    private Resources resources;

    private GL10 gl;

    public void loadTextures() {
        gl.glGenTextures(array.length,array,0);
        for (int i=0; i<textures.size(); i++) {
                Bitmap bitmap = BitmapFactory.decodeResource(resources, (Integer)textures.get(i));
                gl.glBindTexture(GL10.GL_TEXTURE_2D,array[i]);
                GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
                gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
                gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_NEAREST);
                bitmap.recycle();
        }

    }

    public void setResources(Resources resources) {
        this.resources = resources;
    }

    public void setGraphicsContext(Object gl) {
        this.gl = (GL10) gl;
    }
}
