package robotgame.object;

import org.apache.commons.lang.mutable.MutableInt;
import robotgame.world.Direction;
import robotgame.world.Position;
import robotgame.world.MapObject;

public class Rubbish extends WorldObject {

    private int quantity;
	private Direction direction;

    public Rubbish(Position p, int quantity) {
        super(MapObject.RUBBISH.getIntValue(),p,true,false,false,true);
        if( quantity <= 0 )
            this.quantity = 1;
        else
            this.quantity = quantity;
        int type = 1;
        direction = Direction.randDirection();
    }

    public void evolve() {

    }

    public boolean conditionalMovement(final WorldObject worldObject, final Direction direction, final int MaxPower, MutableInt usedPower) {
        return true;
    }

    public void cleaning(final int maxPower, MutableInt usedPower) {
/*	int maxCleaned,needEnergy;
	if ( type == 1 ) 
		maxCleaned = 20;

	if (maxCleaned > quantity)	
		maxCleaned = quantity;
	
	needEnergy = 2 * maxCleaned;

	if (needEnergy > maxPower) {
		quantity -= maxPower/2;
		usedPower = maxPower;
	}
	else {
		usedPower = needEnergy;
		quantity -= maxCleaned;*/
        quantity -= 20;

        if(quantity <= 0)
            getWorld().deleteMe(this);
    }

    public Direction getDirection() {
        return direction;
    }

}