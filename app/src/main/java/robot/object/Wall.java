package robot.object;

import org.apache.commons.lang.mutable.MutableInt;
import robot.Direction;
import robot.MapObject;
import robot.Position;

public class Wall extends WorldObject {
    private int strength;

    public Wall(Position p, int strength) {
        super(MapObject.WALL.getIntValue(),p,false, false, false, false);
        if (strength > 100)
            this.strength = strength;
    }

    public void evolve()  {

    }

    public boolean conditionalMovement(final WorldObject worldObject, final Direction direction,final int maxPower, MutableInt usedPower) {
        usedPower.setValue(maxPower);
        return false;	// -1
    }

}