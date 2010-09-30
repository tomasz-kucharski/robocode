package robot;

public enum WorldObjectVerifier {

    FLOOR       (0),
    FURNITURE   (5),
    ROBOT       (2),
    RUBBISH     (1),
    WALL        (3),
    DEPOT       (4),
    UNKNOWN     (-1);


    private int intValue;


    public int getIntValue() {
        return intValue;
    }

    WorldObjectVerifier(int intValue) {

        this.intValue = intValue;
    }

    public static int getWorldObjectByName(String name) {
        WorldObjectVerifier verifier = WorldObjectVerifier.valueOf(name);
        if (verifier != null) {
            return verifier.getIntValue();
        } else {
            return UNKNOWN.getIntValue();
        }
    }

}