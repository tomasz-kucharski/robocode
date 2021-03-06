package robotgame.loader.obj;

import org.junit.Test;
import org.mockito.Mockito;
import robotgame.loader.obj.OBJFileLoader;
import robotgame.loader.obj.OBJGroup;

import java.io.BufferedReader;
import java.io.IOException;

import static org.fest.assertions.Assertions.assertThat;

/**
 * @author tomekk
 * @since 2010-11-29, 17:51:45
 */
public class TestOBJFileLoader {

    @Test
    public void shouldLoadSimpleModel() throws IOException {
        //given
        OBJFileLoader fileLoader = new OBJFileLoader();
        BufferedReader bufferedReader = createSimpleReaderWithoutFaces();

        //when
        fileLoader.load(bufferedReader);
        OBJGroup group = fileLoader.getModel().getCurrentGroup();

        //then
        assertThat(group.getVertexBuffer().limit()).isEqualTo(24);
        assertThat(group.getNormalBuffer().limit()).isEqualTo(21);
        assertThat(group.getFaceBuffer()).isNull();
        Mockito.verify(bufferedReader);

    }

    @Test
    public void shouldLoadCubeWithoutMaterials() throws IOException {
        //given
        OBJFileLoader fileLoader = new OBJFileLoader();
        BufferedReader bufferedReader = createReaderWithCube();

        //when
        fileLoader.load(bufferedReader);
        OBJGroup group = fileLoader.getModel().getCurrentGroup();

        //then
        assertThat(group.getVertexBuffer().limit()).isEqualTo(24);
        assertThat(group.getNormalBuffer().limit()).isEqualTo(21);
        assertThat(group.getFaceBuffer().limit()).isEqualTo(12*3*2);
        Mockito.verify(bufferedReader);

    }

    private BufferedReader createReaderWithCube() throws IOException {
        BufferedReader bufferedReader = Mockito.mock(BufferedReader.class);
        Mockito.when(bufferedReader.readLine()).thenReturn(
                "# Blender3D v249 OBJ File: ",
                "# www.blender3d.org",
                "mtllib box.mtl",
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
                "usemtl Material.001",
                "s off",
                "f 5//1 1//1 4//1",
                "f 5//1 4//1 8//1",
                "f 3//2 7//2 8//2",
                "f 3//2 8//2 4//2",
                "f 2//3 6//3 3//3",
                "f 6//3 7//3 3//3",
                "f 1//4 5//4 2//4",
                "f 5//5 6//5 2//5",
                "f 5//6 8//6 6//6",
                "f 8//6 7//6 6//6",
                "f 1//7 2//7 3//7",
                "f 1//7 3//7 4//7",
                null
        );
        return bufferedReader;
    }


    private BufferedReader createSimpleReaderWithoutFaces() throws IOException {
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
