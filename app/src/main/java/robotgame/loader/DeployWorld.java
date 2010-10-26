package robotgame.loader;

import robotgame.world.Direction;
import robotgame.world.MapObject;
import robotgame.world.Position;
import robotgame.world.World;
import robotgame.object.*;
import robotgame.object.robot.Robot;

import java.io.*;

public class DeployWorld {

    private World modelWorld;
    BufferedReader map;

    private int columns;
    private int rows;
    private WorldObject robot;
    private static final String SEPARATOR = "\t";

    public DeployWorld(BufferedReader map) throws IOException {
        this.map = map;
        loadMapSize();
        modelWorld = new World(columns,rows);
        loadWorld();
        modelWorld.checkValidate();
    }

    private void loadMapSize() throws IOException {
        String line = map.readLine();
        line = map.readLine();
        String[] lineFragments = line.split(SEPARATOR);
        columns = Integer.parseInt(lineFragments[1]);

        line = map.readLine();
        lineFragments = line.split(SEPARATOR);
        rows = Integer.parseInt(lineFragments[1]);
    }



    private void loadWorld() throws IOException {
        Position p = new Position();
        String line = map.readLine();
        while (line != null) {
            String[] lineFragments = line.split(SEPARATOR);
            p.x = Integer.parseInt(lineFragments[0]);
            p.y = Integer.parseInt(lineFragments[1]);
            String type = lineFragments[2];
            int data = Integer.parseInt(lineFragments[3]);
            if ("ROBOT".equals(type)) {
                int data2 = Integer.parseInt(lineFragments[4]);
                String direction = lineFragments[5];
                String name = lineFragments[6];
                String fileName = lineFragments[7];
                loadObject(type,p,data,data2,direction,name,new File("d:\\home\\projects\\robot\\app\\src\\main\\resources\\intelligence\\"+fileName));
            } else {
                loadObject(type,p,data);
            }
            line = map.readLine();
        }
    }


    private boolean loadObject(String type, Position p, int data) throws IOException {
        return loadObject(type,p,data,0,null,null,null);
    }

    private boolean loadObject(String type, Position p, int data, int data2, String direction, String name, File fileName) throws IOException {
        WorldObject worldObject = null;

        int typeOfWorldObject = MapObject.getWorldObjectByName(type);

        //CREATE ROBOT
        if ( typeOfWorldObject == MapObject.ROBOT.getIntValue()) {
            worldObject = new Robot(p,columns,rows,name, Direction.valueOf(direction),data,data2,fileName);
            robot = worldObject;
        }
        //CREATE FLOOR
        else if( typeOfWorldObject == MapObject.FLOOR.getIntValue()) {
            worldObject = new Floor(p,data);
        }
        //CREATE WALL
        else if( typeOfWorldObject == MapObject.WALL.getIntValue())
            worldObject = new Wall(p,data);
            //CREATE RUBBISH
        else if( typeOfWorldObject == MapObject.RUBBISH.getIntValue())
            worldObject = new Rubbish(p,data);
            //CREATE DEPOT
        else if( typeOfWorldObject == MapObject.DEPOT.getIntValue())
            worldObject = new Depot(p);
            //CREATE FURNITURE
        else if( typeOfWorldObject == MapObject.FURNITURE.getIntValue())
            worldObject = new Furniture(p,data);
        //ADD TO WORLD
        return modelWorld.setCell(p, worldObject);
    }

    public World getWorld()
    {
        return modelWorld;
    }

    public WorldObject getRobot()
    {
        return robot;
    }

}