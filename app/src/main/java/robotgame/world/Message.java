package robotgame.world;

import robotgame.object.WorldObject;

public class Message {
    public int information;
    public WorldObject worldObject;

    public static final int CHECKED = 1;
    public static final int CLEANED = 2;
    public static final int HELP = 3;

    public Message(WorldObject worldObject, int information) {
        this.worldObject = worldObject;
        this.information = information;
    }
}