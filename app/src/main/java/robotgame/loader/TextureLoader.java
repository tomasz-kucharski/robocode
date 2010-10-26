package robotgame.loader;

import javax.media.opengl.GL;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author tomekk
 * @since 2010-10-20, 18:01:51
 */
public class TextureLoader {

    public static final int TEXTURE_SIZE = 7;

    public static final int FLOOR = 0;
    public static final int TABLE = 1;
    public static final int BOX = 2;
    public static final int GRASS = 3;
    public static final int START = 4;
    public static final int ROBOT = 5;
    public static final int FURNITURE = 6;


    public static int[] array = new int[TEXTURE_SIZE];
    int i=-1;

    public void loadTextures(GL gl) {
        gl.glGenTextures(TEXTURE_SIZE,array,0);
        loadTextures(gl,new File("app/src/main/resources/floor.bmp"),64,64);
        loadTextures(gl,new File("app/src/main/resources/table.bmp"),256,256);
        loadTextures(gl,new File("app/src/main/resources/box.bmp"),256,256);
        loadTextures(gl,new File("app/src/main/resources/grass.bmp"),256,256);
        loadTextures(gl,new File("app/src/main/resources/start.bmp"),256,256);
        loadTextures(gl,new File("app/src/main/resources/robot.bmp"),256,256);
        loadTextures(gl,new File("app/src/main/resources/furniture.bmp"),128,128);
    }

    public int loadTextures(GL gl, File file, int width, int height) {
        i++;
        try {
            FileInputStream inputStream = new FileInputStream(file);
            MappedByteBuffer channel = inputStream.getChannel().map(FileChannel.MapMode.READ_ONLY,0,inputStream.available());
            gl.glBindTexture(GL.GL_TEXTURE_2D,array[i]);
            gl.glTexParameteri(GL.GL_TEXTURE_2D,GL.GL_TEXTURE_MIN_FILTER,GL.GL_LINEAR);	// Linear Filtering
            gl.glTexParameteri(GL.GL_TEXTURE_2D,GL.GL_TEXTURE_MAG_FILTER,GL.GL_LINEAR);	// Linear Filtering
            gl.glTexImage2D(GL.GL_TEXTURE_2D, 0, 3, width, height, 0, GL.GL_RGB, GL.GL_UNSIGNED_BYTE,channel);

        } catch (IOException e) {
            System.out.println(System.getProperty("user.dir"));
            e.printStackTrace();  
        }
        return i;
    }

//    public static getTexture(int i) {
//
//    }
}
