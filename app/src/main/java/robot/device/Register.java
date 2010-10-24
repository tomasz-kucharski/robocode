package robot.device;

/**
 * @author tomekk
 * @since 2010-10-24, 23:43:27
 */
public class Register {

    public static final int REGISTRY_MAX_SIZE = 32;

    private int[] register = new int[REGISTRY_MAX_SIZE];
    private static final int ACCU_REGISTER = 0;


    public int getAccu() {
        return register[ACCU_REGISTER];
    }
}
