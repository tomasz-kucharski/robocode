public class RobotBattery {

    private 	int capacity;
    private final int maxCapacity;
    private int actualDischarge;

    public RobotBattery(int capacity)
    {
        maxCapacity = capacity;
        if(capacity < 100 )
            this.capacity = 100;
        else
            this.capacity = capacity;
        actualDischarge = 0;
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
        return (capacity == 0) ? true : false;
    }

    //todo return int ref
    public int plug()
    {
        return  actualDischarge;
    }

    public void unplug()
    {
        //discharge(actualDischarge);
        actualDischarge = 0;
    }


}