package robot;

import robot.object.WorldObject;

public class WorldObjectList extends List<WorldObject> {


    public boolean isObjectByName(int className)
    {
        boolean check = false;
        WorldObject temp;
        if (!setToFirst()) return false;
        while  ( (temp = getNext() ) != null )
            if (temp.getClassName() == className)
                check = true;
        return check;
    }


    public WorldObject getObjectByName(int className)
    {
        WorldObject temp;
        if (!setToFirst()) return null;
        while  ( (temp = getNext() ) != null )
            if (temp.getClassName() == className)
                return temp;
        return null;
    }
}
