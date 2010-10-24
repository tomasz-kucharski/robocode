import static org.fest.assertions.Assertions.*;
import org.junit.Assert;
import org.junit.Test;
import robot.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

/**
 * @author tomekk
 * @since 2010-10-01, 18:56:27
 */
public class ProgramLoadingTest {


    @Test
    public void shouldBeCommentedIfStartedWithDash() throws IOException {
        // given
        BufferedReader programReader = createProgramReader("# sample comment");
        RobotProgramLoader loader = new RobotProgramLoader(programReader);

        //when
        boolean decision = loader.isLineCommented(programReader.readLine());
        //then
        Assert.assertTrue(decision);

    }

    @Test
    public void shouldLoadFourLinesProgram() throws IOException {

        //given
        BufferedReader programReader = createProgramReader("# Comment\n" +
                "1\tMEMFRONT\n" +
                "0\tJEQUAL\t=\tUNKNOWN\t10\t\n" +
                "0\tJEQUAL\t=\tRUBBISH\t20\t\n" +
                "12\tJEQUAL\t=\tEMPTY\t30\t"
        );

        //when

        RobotProgramLoader loader = new RobotProgramLoader(programReader);
        loader.loadProgram();
        ProgramList programList = loader.getProgram();

        //then
        assertThat(programList.size()).isEqualTo(4);
    }

    @Test
    public void linesNumberShouldBeTheSameAsInListing() throws IOException {

        //given
        BufferedReader programReader = createProgramReader("# Comment\n" +
                "1\tMEMFRONT\n" +
                "# Second Comment\n" +
                "0\tJEQUAL\t=\tUNKNOWN\t10\t\n" +
                "0\tJEQUAL\t=\tRUBBISH\t20\t\n" +
                "12\tJEQUAL\t=\tEMPTY\t30\t"
        );

        //when

        RobotProgramLoader loader = new RobotProgramLoader(programReader);
        loader.loadProgram();
        ProgramList programList = loader.getProgram();

        //then
        assertThat(programList.next().getLine()).isEqualTo(1);
        assertThat(programList.next().getLine()).isEqualTo(3);
        assertThat(programList.next().getLine()).isEqualTo(4);
        assertThat(programList.next().getLine()).isEqualTo(5);
    }

    @Test
    public void shouldProperlyLoadJEQUALCommand() throws IOException {

        //given
        BufferedReader programReader = createProgramReader("# Comment\n" +
                "13\tJEQUAL\t=\tUNKNOWN\t10\t\n"
        );

        //when

        RobotProgramLoader loader = new RobotProgramLoader(programReader);
        loader.loadProgram();
        ProgramList programList = loader.getProgram();
        Instruction instruction = programList.next();

        //then

        assertThat(instruction.getLine()).isEqualTo(1);
        assertThat(instruction.getLabel()).isEqualTo(13);
        assertThat(instruction.getRozkaz()).isEqualTo(Order.JEQUAL);
        assertThat(instruction.getOperation()).isEqualTo(RobotProcessor.OPEQUAL);
        assertThat(instruction.getValue1()).isEqualTo(RobotProcessor.UNKNOWN);
        assertThat(instruction.getValue2()).isEqualTo(10);
    }

    private BufferedReader createProgramReader(String listing) {
        BufferedReader programReader = new BufferedReader(new StringReader(listing.toString()));
        return programReader;
    }
}
