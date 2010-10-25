package robot;

import robot.object.WorldObject;

public class RobotScanner {
    private Progress progress;

    public enum Progress {
        STARTED,STOPPED;
    }

    private Robot owner;
    private RobotMemory memory;
    private Position p;
    private int range;

    public RobotScanner(Robot owner, int range) {
        p = new Position(0,0);
        if(range <= 0) {
            this.range = 1;
        } else {
            this.range = range;
        }

        this.owner = owner;
        this.memory = owner.getMemory();
    }

    public void setProgress(Progress started) {
        this.progress = started;
    }

    public WorldObject scanMyCell(int className)
    {
        return owner.getWorld().getObject(owner.getPosition(),className);
    }

    public void scan() {
        Direction direction = memory.getDirection();
        int i;
        p.x = owner.getPosition().x;
        p.y = owner.getPosition().y;

        p = direction.getLeft().computePosition(p);
        if (memory.checkPosition(p)) {
            for(i=0; i< range; i++)
            {
                p = direction.computePosition(p);
                if(memory.checkPosition(p))
                    memory.setMemoryCell(p,verify(p));
            }
        }

        p.x = owner.getPosition().x;
        p.y = owner.getPosition().y;

        if (memory.checkPosition(p))
        {
            for(i=0; i< range; i++)
            {
                p = direction.computePosition(p);
                if(memory.checkPosition(p))
                    memory.setMemoryCell(p,verify(p));
            }
        }

        p.x = owner.getPosition().x;
        p.y = owner.getPosition().y;

        p = direction.getRight().computePosition(p);
        if (memory.checkPosition(p))
        {
            for(i=0; i< range; i++)
            {
                p = direction.computePosition(p);
                if(memory.checkPosition(p))
                    memory.setMemoryCell(p,verify(p));
            }
        }
    }

    //todo looks bad :)
    private RobotMemoryObject verify(Position p) {
        if(owner.getWorld().getObject(p, MapObject.DEPOT.getIntValue()) != null)
            return RobotMemoryObject.DEPOT;
        if(owner.getWorld().getObject(p, MapObject.FURNITURE.getIntValue()) != null)
            return RobotMemoryObject.MOVABLE;
        if(owner.getWorld().getObject(p, MapObject.RUBBISH.getIntValue()) != null)
            return RobotMemoryObject.RUBBISH;
        if(owner.getWorld().getObject(p, MapObject.WALL.getIntValue()) != null)
            return RobotMemoryObject.UNMOVABLE;
        if(owner.getWorld().getObject(p, MapObject.ROBOT.getIntValue()) != null)
            return RobotMemoryObject.ROBOT;
        return RobotMemoryObject.EMPTY;
    }


    public int getScanerZakres()
    {
        return range;
    }

}