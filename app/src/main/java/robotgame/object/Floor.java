package robotgame.object;

import org.apache.commons.lang.mutable.MutableInt;
import robotgame.world.Direction;
import robotgame.world.Position;
import robotgame.world.MapObject;

public class Floor extends WorldObject {
    private int slippery; //od 1 do 10

    public Floor(Position p, int slippery) {
        super(MapObject.FLOOR.getIntValue(),p,true,false,false,true);
        if( (slippery > 0 ) && (slippery <= 10 ) )
            this.slippery = slippery;
        else this.slippery = 1;
    }


    public boolean conditionalMovement(final WorldObject worldObject, final Direction direction,final int MaxPower, MutableInt usedPower) {
        usedPower.add(slippery);
        return true;
    }

    public void evolve() {

    }

}