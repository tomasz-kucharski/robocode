package robot;

/**
 * @author tomekk
 * @since 2010-10-24, 18:41:35
 */
public enum Order {
    SCAN     (1 ,0),
    CLEAN    (2 ,0),
    MOVE     (3 ,0),
    MEMLEFT  (4 ,0),
    MEMRIGHT (5 ,0),
    MEMFRONT (6 ,0),
    MEMBACK  (7 ,0),
    TURNLEFT (8 ,0),
    TURNRIGHT(9 ,0),
    RECEIVE  (10,0),
    FINDPATH (11,0),
    RAND     (12,2),
    STORE    (13,1),
    LOAD     (14,2),
    READ     (15,3),
    INC      (16,1),
    DEC      (17,1),
    JUMP     (18,1),
    JEQUAL   (19,3),
    JNEQUAL  (20,3),
    JGT      (21,3),
    JLT      (22,3),
    JUMPF    (23,1),
    JEQUALF  (24,3),
    JNEQUALF (25,3),
    JGTF     (26,3),
    JLTF     (27,3),
    RETURN   (28,0);


    private int orderNumber;
    private int numberOfArguments;

    Order(int orderNumber, int numberOfArguments) {
        this.orderNumber = orderNumber;
        this.numberOfArguments = numberOfArguments;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public int getNumberOfArguments() {
        return numberOfArguments;
    }
}
