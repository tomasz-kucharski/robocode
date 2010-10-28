package robotgame.world;

import org.apache.commons.lang.mutable.MutableInt;
import robotgame.object.WorldObject;

public class World {

    private WorldMap map;

    public void setMap(WorldMap map) {
        this.map = map;
        this.map.performActionOnWorldObjects(new WorldMap.Command(){

            @Override
            public void performActionOnWorldObject(WorldObject object) {
                object.setWorld(World.this);
            }
        });
    }

    public boolean validateMap(WorldMap map) {
        Position position = new Position(0, 0);
        for(position.x=0; position.x<map.getColumns(); position.x++) {
            for(position.y=0; position.y<map.getRows(); position.y++) {
                if(!map.getCell(position).isObjectByName(MapObject.FLOOR)) {
                    return false;
                }
            }
        }
        return true;
    }

    public void evolve() {
        map.performActionOnWorldObjects(new WorldMap.Command(){
            @Override
            public void performActionOnWorldObject(WorldObject object) {
                object.evolve();
            }
        });
        clearWorld();
    }

    private boolean moveObject(WorldObject object, Direction direction) {
        Position p = direction.computeNextPosition(object.getPosition());
        if (!map.checkPosition(p)) {
            return false;
        }

        map.getCell(object.getPosition()).remove(object);
        map.setCell(p,object);
        return true;
    }

    public boolean move(final WorldObject worldObject, final Direction direction, final int maxPower, MutableInt usedPower) {

        if ( maxPower < usedPower.intValue()) {
            return false;
        }

        Position p = direction.computeNextPosition(worldObject.getPosition());
        if (!map.checkPosition(p)) {
            return false;
        }

        for (WorldObject object : map.getCell(p)) {
            if(!object.conditionalMovement(worldObject,direction,maxPower,usedPower)) {
                return false;
            }
        }
        return moveObject(worldObject, direction);
    }

    public void deleteMe(final WorldObject worldObject) {
        worldObject.setDeleted(true);
    }

    public void clearWorld() {
        map.performActionOnWorldObjects(new WorldMap.Command(){
            @Override
            public void performActionOnWorldObject(WorldObject object) {
                if (object.isDeleted()) {
                    map.getCell(object.getPosition()).remove(object);
                }
            }
        });
    }

    public WorldObject getObject(Position p, MapObject className) {
        if(!map.checkPosition(p)) {
            return null;
        } else {
            return map.getCell(p).getObjectByName(className);
        }
    }
}
