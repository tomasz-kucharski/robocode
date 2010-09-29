package robot.object;

import org.apache.commons.lang.mutable.MutableInt;

public abstract class WorldObject {

//	friend class World;
    //	friend class Eter;
    World world;
    int className;
    Position position;

    protected boolean moved;
    protected boolean deleteMe;
    private boolean flat;
    private boolean movable;
    private boolean intelligent;
    private boolean slide;

    public WorldObject(int className, Position p, boolean flat, boolean intelligent, boolean movable, boolean slide)
    {
        this.flat = flat;
        this.intelligent = intelligent;
        this.movable = movable;
        this.slide = slide;
        this.className = className;

        this.position = new Position(p.x, p.y);
        deleteMe = false;
        moved = false;
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

    public int getClassName()
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

    public abstract boolean conditionalMovement(final WorldObject worldObject, final int direction, final int maxPower, MutableInt usedPower); 
}
