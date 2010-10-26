package robotgame.object;

import org.apache.commons.lang.mutable.MutableInt;
import robotgame.world.Direction;
import robotgame.world.Position;
import robotgame.world.MapObject;

public class Depot extends WorldObject {

    private boolean lighted;                                                                                                               

    public Depot(Position p) {
        super(MapObject.DEPOT.getIntValue(),p,true,false,false,true);
    }

    public boolean conditionalMovement(final WorldObject worldObject, final Direction direction, final int MaxPower, MutableInt usedPower) {
        return true;
    }

    public void evolve() {
        lighted = !lighted;
    }

    public boolean isLighted() {
        return lighted;
    }

}