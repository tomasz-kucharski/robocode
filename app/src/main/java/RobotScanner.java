
public class RobotScanner {

    private Robot owner;
    private RobotMemory memory;
    private Position p;
    private int zakres;

    public RobotScanner(Robot owner, RobotMemory memory, int zakres) {
        p = new Position(0,0);
        if( (zakres <= 0)  )
            this.zakres  = 1;
        else
            this.zakres = zakres;

        this.owner = owner;
        this.memory = memory;

        p = new Position(0,0);
    }

    public WorldObject scanMyCell(int className)
    {
        return owner.getWorld().getObject(owner.getPosition(),className);
    }

    public void scan()
    {
        int direction = memory.getDirection();
        int i = 0;
        p.x = owner.getPosition().x;
        p.y = owner.getPosition().y;

        Direction.computePosition(p,Direction.getLeft(direction));
        if (memory.checkPosition(p)) {
            for(i=0; i<zakres; i++)
            {
                Direction.computePosition(p,direction);
                if(memory.checkPosition(p))
                    memory.setMemoryCell(p,verify(p));
            }
        }

        p.x = owner.getPosition().x;
        p.y = owner.getPosition().y;

        if (memory.checkPosition(p))
        {
            for(i=0; i<zakres; i++)
            {
                Direction.computePosition(p,direction);
                if(memory.checkPosition(p))
                    memory.setMemoryCell(p,verify(p));
            }
        }

        p.x = owner.getPosition().x;
        p.y = owner.getPosition().y;

        Direction.computePosition(p,Direction.getRight(direction));
        if (memory.checkPosition(p))
        {
            for(i=0; i<zakres; i++)
            {
                Direction.computePosition(p,direction);
                if(memory.checkPosition(p))
                    memory.setMemoryCell(p,verify(p));
            }
        }
    }

    private int verify(Position p)
    {
        if(owner.getWorld().getObject(p,WorldObjectVerifier.DEPOT.getIntValue()) != null)
            return RobotProcessor.DEPOT;
        if(owner.getWorld().getObject(p,WorldObjectVerifier.FURNITURE.getIntValue()) != null)
            return RobotProcessor.MOVABLE;
        if(owner.getWorld().getObject(p,WorldObjectVerifier.RUBBISH.getIntValue()) != null)
            return RobotProcessor.RUBBISH;
        if(owner.getWorld().getObject(p,WorldObjectVerifier.WALL.getIntValue()) != null)
            return RobotProcessor.UNMOVABLE;
        if(owner.getWorld().getObject(p,WorldObjectVerifier.ROBOT.getIntValue()) != null)
            return RobotProcessor.ROBOT;
        return RobotProcessor.EMPTY;
    }


    public int getScanerZakres()
    {
        return zakres;
    }

}