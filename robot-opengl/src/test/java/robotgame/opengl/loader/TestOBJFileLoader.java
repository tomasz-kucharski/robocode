package robotgame.opengl.loader;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import robotgame.opengl.loader.obj.OBJFileLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

/**
 * @author tomekk
 * @since 2010-11-29, 17:51:45
 */
public class TestOBJFileLoader {
    private StringBuffer stringBuffer;

    @Test
    public void shouldLoadSimpleModel() throws IOException {
        OBJFileLoader fileLoader = new OBJFileLoader();
        fileLoader.load(initializeSimpleModel());
    }

    private BufferedReader initializeSimpleModel() throws IOException {
        BufferedReader bufferedReader = Mockito.mock(BufferedReader.class);
        Mockito.when(bufferedReader.readLine()).thenReturn(
                "v -2.254464 -0.989700 -0.601697",
                "v -2.254464 -0.989700 1.398303",
                "v -4.254464 -0.989700 1.398302",
                "v -4.254463 -0.989700 -0.601698",
                "v -2.254463 1.010300 -0.601697",
                "v -2.254465 1.010300 1.398303",
                "v -4.254464 1.010300 1.398302",
                "v -4.254464 1.010300 -0.601697",
                "vn 0.000000 0.000000 -1.000000",
                "vn -1.000000 0.000000 0.000000",
                "vn -0.000000 -0.000000 1.000000",
                "vn 1.000000 -0.000000 0.000000",
                "vn 1.000000 0.000000 0.000001",
                "vn 0.000000 1.000000 -0.000000",
                "vn -0.000000 -1.000000 0.000000",
                null
        );
        return bufferedReader;
    }
}
