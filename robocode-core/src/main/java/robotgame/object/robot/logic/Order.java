package robotgame.object.robot.logic;

/**
 * @author tomekk
 * @since 2010-10-24, 18:41:35
 */
public enum Order {
    SCAN     (0),
    CLEAN    (0),
    MOVE     (0),
    MEMLEFT  (0),
    MEMRIGHT (0),
    MEMFRONT (0),
    MEMBACK  (0),
    TURNLEFT (0),
    TURNRIGHT(0),
    RECEIVE  (0),
    FINDPATH (0),
    RAND     (2),
    STORE    (1),
    LOAD     (2),
    INC      (1),
    DEC      (1),
    JUMP     (1),
    JEQUAL   (3),
    JNEQUAL  (3),
    JGT      (3),
    JLT      (3),
    RETURN   (0),
    INVOKE   (1);


    private int numberOfArguments;

    Order(int numberOfArguments) {
        this.numberOfArguments = numberOfArguments;
    }

    public int getNumberOfArguments() {
        return numberOfArguments;
    }
}
