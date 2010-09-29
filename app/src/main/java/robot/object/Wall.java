package robot.object;

import org.apache.commons.lang.mutable.MutableInt;

public class Wall extends WorldObject {
    private int strength;

    public Wall(Position p, int strength) {
        super(WorldObjectVerifier.WALL.getIntValue(),p,false, false, false, false);
        if (strength > 100)
            this.strength = strength;
    }

    public void evolve()  {

    }

    public boolean conditionalMovement(final WorldObject worldObject, final int direction,final int maxPower, MutableInt usedPower) {
        usedPower.setValue(maxPower);
        return false;	// -1
    }

}