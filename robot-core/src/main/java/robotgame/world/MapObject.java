package robotgame.world;

public enum MapObject {

    UNKNOWN     (-1),
    FLOOR       (0),
    RUBBISH     (1),
    FURNITURE   (2),
    ROBOT       (2),
    WALL        (2),
    DEPOT       (2);

    private int level;

    MapObject(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public static int getWorldObjectByName(String name) {
        MapObject verifier = MapObject.valueOf(name);
        if (verifier != null) {
            return verifier.ordinal();
        } else {
            return UNKNOWN.ordinal();
        }
    }

    public static int getMaxLevel() {
        int level = 0;
        for (MapObject object : MapObject.values()) {
            level = Math.max(0,object.getLevel());
        }
        return level;
    }

}