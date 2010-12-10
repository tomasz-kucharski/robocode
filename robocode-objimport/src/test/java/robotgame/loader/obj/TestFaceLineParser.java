package robotgame.loader.obj;

import org.junit.Test;
import robotgame.loader.obj.*;

import static org.fest.assertions.Assertions.assertThat;

/**
 * @author tomekk
 * @since 2010-12-08, 19:58:41
 */
public class TestFaceLineParser {

    @Test
    public void shouldParseLineAsFaceElementVertexOnly() {
        //given
        FaceLineParser faceLineParser = new FaceLineParser();

        //when
        FaceElement element = faceLineParser.parseSingleIndexItem("2");

        //then
        assertThat(element).isInstanceOf(FaceElementVertexOnly.class);
        assertThat(element.toArray()[0]).isEqualTo((short)1);
    }

    @Test
    public void shouldParseLineAsFaceElementVertexNormal() {
        //given
        FaceLineParser faceLineParser = new FaceLineParser();

        //when
        FaceElement element = faceLineParser.parseSingleIndexItem("1//2");

        //then
        assertThat(element).isInstanceOf(FaceElementVertexNormal.class);
    }

    @Test
    public void shouldParseLineAsFaceElementVertexTextureNormal() {
        //given
        FaceLineParser faceLineParser = new FaceLineParser();

        //when
        FaceElement element = faceLineParser.parseSingleIndexItem("1/1/1");

        //then
        assertThat(element).isInstanceOf(FaceElementVertexTextureNormal.class);
    }
}
