package robotgame.android.loader;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;
import robotgame.android.R;
import robotgame.loader.TextureLoader;
import robotgame.opengl.object.*;

import javax.imageio.ImageIO;
import javax.microedition.khronos.opengles.GL;
import javax.microedition.khronos.opengles.GL10;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.Map;

/**
 * @author tomekk
 * @since 2010-10-20, 18:01:51
 */
public class AndroidTextureLoader extends TextureLoader {

    private Resources resources;

    private GL10 gl;

    private static final Map<String,Integer> resourceMap = new HashMap<String, Integer>();
    static {
        resourceMap.put(FloorGL.FLOOR,R.drawable.floor);
        resourceMap.put(FurnitureGL.FURNITURE,R.drawable.furniture);
        resourceMap.put(DepotGL.DEPOT,R.drawable.depot);
        resourceMap.put(RubbishGL.RUBBISH,R.drawable.rubbish);
        resourceMap.put(RobotGL.ROBOT,R.drawable.robot);
        resourceMap.put(WallGL.WALL,R.drawable.wall);
    }

    public void loadTextures() {
        gl.glGenTextures(array.length,array,0);
        for (int i=0; i<textures.size(); i++) {
            Bitmap bitmap = BitmapFactory.decodeResource(resources, resourceMap.get(textures.get(i)));
            gl.glBindTexture(GL10.GL_TEXTURE_2D,array[i]);
            GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
            gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
            gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_NEAREST);
            buildMipmap(gl,bitmap);
            bitmap.recycle();
        }

    }

    public void setResources(Resources resources) {
        this.resources = resources;
    }

    public void setGraphicsContext(Object gl) {
        this.gl = (GL10) gl;
    }

    /**
     * It increased sampleMap.txt rendering from 5,4fps to 6,8fps on android 1.6 G1
     * @param gl
     * @param bitmap
     */
    private void buildMipmap(GL10 gl, Bitmap bitmap) {
        //
        int level = 0;
        //
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();
        //
        while(height >= 1 || width >= 1) {
            //First of all, generate the texture from our bitmap and set it to the according level
            GLUtils.texImage2D(GL10.GL_TEXTURE_2D, level, bitmap, 0);
            //
            if(height == 1 || width == 1) {
                break;
            }
            //Increase the mipmap level
            level++;
            //
            height /= 2;
            width /= 2;
            Bitmap bitmap2 = Bitmap.createScaledBitmap(bitmap, width, height, true);

            //Clean up
            bitmap.recycle();
            bitmap = bitmap2;
        }
    }
}
