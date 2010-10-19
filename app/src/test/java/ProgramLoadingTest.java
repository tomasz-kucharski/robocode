import org.junit.Assert;
import org.junit.Test;
import robot.RobotProgramLoader;

import java.io.IOException;

/**
 * @author tomekk
 * @since 2010-10-01, 18:56:27
 */
public class ProgramLoadingTest {


    @Test
    public void shouldBeCommentedIfStartedWithDash() throws IOException {
        // given
        String commented = "# sample comment";
        RobotProgramLoader loader = new RobotProgramLoader(null,null);
        //when
        boolean decision = loader.isLineCommented(commented);
        //then
        Assert.assertTrue(decision);

    }
}
