package robot.object;

import org.apache.commons.lang.mutable.MutableInt;
import robot.Position;
import robot.WorldObjectVerifier;

public class Depot extends WorldObject {

    private int efficiency;
    private boolean lighted;

    public Depot(Position p, int slippery) {
        super(WorldObjectVerifier.DEPOT.getIntValue(),p,true,false,false,true);
        if( efficiency < 0 )
            this.efficiency = 100;
        else
            this.efficiency = efficiency;
        lighted = false;
    }

    public boolean conditionalMovement(final WorldObject worldObject, final int direction, final int MaxPower, MutableInt usedPower)
    {
        return true;
    }

    public void evolve()
    {
        lighted = !lighted;
    }

    public boolean getLighted()
    {
        return lighted;
    }

}