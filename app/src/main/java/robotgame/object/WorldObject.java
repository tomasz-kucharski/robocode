package robotgame.object;

import org.apache.commons.lang.mutable.MutableInt;
import robotgame.world.Direction;
import robotgame.world.MapObject;
import robotgame.world.Position;
import robotgame.world.World;

public abstract class WorldObject {

    public World world;
    MapObject className;
    public Position position;

    public boolean deleteMe;
    private boolean flat;
    private boolean movable;
    private boolean intelligent;
    private boolean slide;

    public WorldObject(MapObject mapObject, Position p, boolean flat, boolean intelligent, boolean movable, boolean slide)
    {
        this.position = new Position(p.x, p.y);
        this.flat = flat;
        this.intelligent = intelligent;
        this.movable = movable;
        this.slide = slide;
        this.className = mapObject;

        deleteMe = false;
    }

    public boolean isSlide()
    {
        return slide;
    }

    public boolean isFlat()
    {
        return flat;
    }

    public boolean isMovable()
    {
        return movable;
    }

    public boolean isIntelligent()
    {
        return intelligent;
    }

    public MapObject getClassName()
    {
        return className;
    }

    public World getWorld()
    {
        return world;
    }

    public Position getPosition()
    {
        return position;
    }

    public abstract boolean conditionalMovement(final WorldObject worldObject, final Direction direction, final int maxPower, MutableInt usedPower);

    public abstract void evolve();
}
