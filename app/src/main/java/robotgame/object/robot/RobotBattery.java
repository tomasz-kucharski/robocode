package robotgame.object.robot;

import org.apache.commons.lang.mutable.MutableInt;
import robotgame.object.Depot;

public class RobotBattery {

    private 	int capacity;
    private final int maxCapacity;
    private MutableInt actualDischarge;

    public RobotBattery(int capacity)
    {
        maxCapacity = capacity;
        if(capacity < 100 )
            this.capacity = 100;
        else
            this.capacity = capacity;
        actualDischarge = new MutableInt(0);
    }

    public int getCapacity()
    {
        return capacity;
    }


    private void discharge(int amount)
    {
        capacity -= amount;
        if (capacity < 0 )
            capacity = 0;
    }

    public void charge(Depot depot)
    {

    }

    public int getMaxCapacity()
    {
        return maxCapacity;
    }

    public int getDischargeLevel()
    {
        return (maxCapacity*100)/capacity;
    }


    public boolean isEmpty()
    {
        return (capacity == 0);
    }

    public MutableInt plug()
    {
        return  actualDischarge;
    }

    public void unplug()
    {
        //discharge(actualDischarge);
        actualDischarge.setValue(0);
    }


}