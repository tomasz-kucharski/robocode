package robotgame.object;

import org.apache.commons.lang.mutable.MutableInt;
import robotgame.world.Direction;
import robotgame.world.MapObject;
import robotgame.world.Position;

public class Wall extends WorldObject {
    private int strength;

    public Wall(Position p, int strength) {
        super(MapObject.WALL,p,false, false, false, false);
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