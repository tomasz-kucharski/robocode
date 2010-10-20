package robot;

import javax.media.opengl.GL;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.IntBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author tomekk
 * @since 2010-10-20, 18:01:51
 */
public class TextureLoader {

    IntBuffer buffer = IntBuffer.allocate(10);

    public void loadTextures(GL gl) {
        try {
            FileInputStream inputStream = new FileInputStream(new File("app/src/main/resources/floor.bmp"));
            MappedByteBuffer channel = inputStream.getChannel().map(FileChannel.MapMode.READ_ONLY,0,inputStream.available());

            gl.glGenTextures(1,buffer);
            gl.glBindTexture(GL.GL_TEXTURE_2D,1);
            gl.glTexImage2D(GL.GL_TEXTURE_2D, 0, 3, 64, 64, 0, GL.GL_RGB, GL.GL_UNSIGNED_BYTE,channel);
            gl.glTexParameteri(GL.GL_TEXTURE_2D,GL.GL_TEXTURE_MIN_FILTER,GL.GL_LINEAR);	// Linear Filtering
            gl.glTexParameteri(GL.GL_TEXTURE_2D,GL.GL_TEXTURE_MAG_FILTER,GL.GL_LINEAR);	// Linear Filtering

        } catch (IOException e) {
            System.out.println(System.getProperty("user.dir"));
            e.printStackTrace();  
        }
    }
}
