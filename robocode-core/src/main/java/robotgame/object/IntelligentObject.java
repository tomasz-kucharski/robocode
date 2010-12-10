package robotgame.object;

import robotgame.world.MapObject;
import robotgame.world.Position;

public abstract class IntelligentObject extends WorldObject {

    String name;

    public IntelligentObject(MapObject className, Position p, boolean  flat, boolean movable,boolean slide, String name) {
        super(className,p,flat,true,movable,slide);
        this.name = name;
    }


    public void evolve()
    {
        think();
    }

    public abstract void think();

}