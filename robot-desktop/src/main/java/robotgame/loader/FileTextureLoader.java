package robotgame.loader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.media.opengl.GL;
import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL10Impl;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tomekk
 * @since 2010-10-20, 18:01:51
 */
public class FileTextureLoader extends TextureLoader {
    private static final Logger log = LoggerFactory.getLogger(FileTextureLoader.class);

    private GL gl;

    private String contextPath;

    public FileTextureLoader(String contextPath) {
        this.contextPath = contextPath;
    }

    public void loadTextures() {
        gl.glGenTextures(array.length,array,0);

        for (int i=0; i<textures.size(); i++) {
            File input = new File(contextPath+"/textures/"+textures.get(i)+".bmp");
            try {
                BufferedImage image = ImageIO.read(input);
                int width1 = image.getWidth();
                int height1 = image.getHeight();
                FileInputStream inputStream = new FileInputStream(input);
                Buffer channel = inputStream.getChannel().map(FileChannel.MapMode.READ_ONLY,0,inputStream.available());

                gl.glBindTexture(GL.GL_TEXTURE_2D,array[i]);
                gl.glTexParameteri(GL.GL_TEXTURE_2D,GL.GL_TEXTURE_MIN_FILTER,GL.GL_LINEAR);	// Linear Filtering
                gl.glTexParameteri(GL.GL_TEXTURE_2D,GL.GL_TEXTURE_MAG_FILTER,GL.GL_LINEAR);	// Linear Filtering
                gl.glTexImage2D(GL.GL_TEXTURE_2D, 0, 3, width1, height1, 0, GL.GL_RGB, GL.GL_UNSIGNED_BYTE,channel);

            } catch (IOException e) {
                log.warn("User path: "+System.getProperty("user.dir")+", File: "+input.getAbsolutePath());
                System.out.println();
                e.printStackTrace();
            }
        }
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    public void setGraphicsContext(Object gl) {
        this.gl = ((GL10Impl) gl).getGl();
    }
}
