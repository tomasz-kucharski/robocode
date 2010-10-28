package robotgame.loader;

import robotgame.world.*;
import robotgame.object.*;
import robotgame.object.robot.Robot;

import java.io.*;

public class DeployWorld {

    private WorldMap worldMap;
    BufferedReader map;

    private int columns;
    private int rows;
    private Robot robot;
    private static final String SEPARATOR = "\t";

    public DeployWorld(BufferedReader map) throws IOException {
        this.map = map;
        loadMapSize();
        worldMap = new WorldMap(columns,rows);
        loadWorld();
    }

    public WorldMap getWorldMap() {
        return worldMap;
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
                loadObject(type,p,data,data2,direction,name,new File("d:\\home\\projects\\robot\\robot-desktop\\src\\main\\resources\\intelligence\\"+fileName));
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

        MapObject objectType = MapObject.valueOf(type);

        switch (objectType) {
            case ROBOT :
                robot = new Robot(p,columns,rows,name, Direction.valueOf(direction),data,data2,fileName);
                worldObject = robot;
                break;
            case FLOOR :
                worldObject = new Floor(p,data);
                break;
            case WALL:
                worldObject = new Wall(p,data);
                break;
            case  RUBBISH :
                worldObject = new Rubbish(p,data);
                break;
            case DEPOT:
                worldObject = new Depot(p);
                break;
            case FURNITURE:
                worldObject = new Furniture(p,data);
                break;
        }
        return worldMap.setCell(p, worldObject);
    }

    public Robot getRobot() {
        return robot;
    }

}