package robotgame.loader;

import robotgame.object.*;
import robotgame.object.robot.ProgramList;
import robotgame.object.robot.Robot;
import robotgame.world.Direction;
import robotgame.world.MapObject;
import robotgame.world.Position;
import robotgame.world.WorldMap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

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
                File file = new File("d:\\home\\projects\\robot\\robot-desktop\\src\\main\\resources\\intelligence\\" + fileName);
                RobotProgramLoader loader = new RobotProgramLoader(new BufferedReader(new FileReader(file)));
                loadObject(type,p,data,data2,direction,name, loader.loadProgram());
            } else {
                loadObject(type,p,data);
            }
            line = map.readLine();
        }
    }


    private boolean loadObject(String type, Position p, int data) throws IOException {
        return loadObject(type,p,data,0,null,null,null);
    }

    private boolean loadObject(String type, Position p, int data, int data2, String direction, String name, ProgramList program) throws IOException {
        WorldObject worldObject = null;

        MapObject objectType = MapObject.valueOf(type);

        switch (objectType) {
            case ROBOT :
                robot = new Robot(p,columns,rows,name, Direction.valueOf(direction),data,data2,program);
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