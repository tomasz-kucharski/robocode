package robot.object;

import org.apache.commons.lang.mutable.MutableInt;
import robot.Direction;
import robot.Position;
import robot.WorldObjectVerifier;

import java.util.Random;

public class Furniture extends WorldObject {

    private int weight;
    private int direction;
    private int type;

    public Furniture(Position p, int weight) {
        super(WorldObjectVerifier.FURNITURE.getIntValue(),p,false,false,true,true);
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

    public boolean conditionalMovement(final WorldObject worldObject, final int direction,final int MaxPower, MutableInt usedPower) {
        usedPower.add(weight);
        return getWorld().move(this,direction,MaxPower,usedPower);
    }

    public int getDirection()
    {
        return direction;
    }

    public int getType()
    {
        return type;
    }

}