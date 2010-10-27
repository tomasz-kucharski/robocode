package robotgame.object;

import org.apache.commons.lang.mutable.MutableInt;
import robotgame.world.Direction;
import robotgame.world.Position;
import robotgame.world.MapObject;

import java.util.Random;

public class Furniture extends WorldObject {

    private int weight;
    private Direction direction;
    private int type;

    public Furniture(Position p, int weight) {
        super(MapObject.FURNITURE,p,false,false,true,true);
        if( weight < 0 ) {
            this.weight = 1;
        }
        else {
            this.weight = weight;
        }
        direction = Direction.randDirection();
        type = new Random().nextInt(4);

    }

    public void evolve() {

    }

    public boolean conditionalMovement(final WorldObject worldObject, final Direction direction,final int MaxPower, MutableInt usedPower) {
        usedPower.add(weight);
        return getWorld().move(this,direction,MaxPower,usedPower);
    }

    public Direction getDirection()
    {
        return direction;
    }

    public int getType()
    {
        return type;
    }

}