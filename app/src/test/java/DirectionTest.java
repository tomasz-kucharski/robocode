import static org.fest.assertions.Assertions.*;
import org.junit.Test;
import robot.Direction;

/**
 * @author tomekk
 * @since 2010-10-25, 21:38:50
 */
public class DirectionTest {

    @Test
    public void shouldTurnLeft() {

        //when
        Direction direction = Direction.WEST;
        //then
        assertThat(direction.getLeft()).isEqualTo(Direction.SOUTH);
    }
}
