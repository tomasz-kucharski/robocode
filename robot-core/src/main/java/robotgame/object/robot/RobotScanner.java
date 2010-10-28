package robotgame.object.robot;

import robotgame.object.WorldObject;
import robotgame.world.Direction;
import robotgame.world.MapObject;
import robotgame.world.Position;

public class RobotScanner {

    public enum Progress {
        STARTED,
        STOPPED
    }

    private Progress progress;
    private Robot owner;
    private RobotMemory memory;
    private int range;

    public RobotScanner(Robot owner, int range) {
        this.range = range;
        if(range <= 0) {
            this.range = 1;
        }

        this.owner = owner;
        this.memory = owner.getMemory();
    }

    public void setProgress(Progress started) {
        this.progress = started;
    }

    public WorldObject scanMyCell(MapObject className) {
        return owner.getWorld().getObject(owner.getPosition(),className);
    }

    public void scan() {
        scanCell(memory.getDirection());
        scanCell(memory.getDirection().getLeft());
        scanCell(memory.getDirection().getRight());
    }

    private void scanCell(Direction direction) {
        Position p = direction.computeNextPosition(owner.getPosition());
        scanSelectedPosition(p);
    }

    private void scanSelectedPosition(Position p) {
        if(memory.checkPosition(p))
            memory.setMemoryCell(p,verify(p));
    }

    //todo looks bad :)
    private RobotMemoryObject verify(Position p) {
        if(owner.getWorld().getObject(p, MapObject.DEPOT) != null)
            return RobotMemoryObject.DEPOT;
        if(owner.getWorld().getObject(p, MapObject.FURNITURE) != null)
            return RobotMemoryObject.MOVABLE;
        if(owner.getWorld().getObject(p, MapObject.RUBBISH) != null)
            return RobotMemoryObject.RUBBISH;
        if(owner.getWorld().getObject(p, MapObject.WALL) != null)
            return RobotMemoryObject.UNMOVABLE;
        if(owner.getWorld().getObject(p, MapObject.ROBOT) != null)
            return RobotMemoryObject.ROBOT;
        return RobotMemoryObject.EMPTY;
    }


    public int getScanerZakres()
    {
        return range;
    }

}