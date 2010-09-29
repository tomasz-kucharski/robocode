import robot.object.WorldObject;

public abstract class IntelligentObject extends WorldObject {

    String name;

    public IntelligentObject(int className, Position p, boolean  flat, boolean movable,boolean slide, String name) {
        super(className,p,flat,true,movable,slide);
        this.name = name;
    }


    public void evolve()
    {
        think();
    }

    public abstract void think();

}