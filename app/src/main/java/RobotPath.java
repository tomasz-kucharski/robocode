import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Stack;

public class RobotPath extends Stack<Integer> {
    public static final int LEFT = 1;
    public static final int RIGHT = 2;
    public static final int MOVE = 3;
}