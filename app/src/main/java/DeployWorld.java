import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class DeployWorld {

    private World modelWorld;
    FileInputStream in;
    private String token;
    private int size;

    private int columns;
    private int rows;
    private WorldObject robot;

    public DeployWorld(final String file) throws FileNotFoundException {
	size = 40;
	in = new FileInputStream(file);
	token = new char[size];

	rows = 0;
	columns = 0;

	if(init())
		modelWorld = new World(columns,rows);
	else 
		System.exit(10);
	if (!loadWorld())
		System.exit(20);
	if (!modelWorld.checkValidate())
		System.exit(30);
}

private boolean init() {
	do
	{	
		getToken();
		if(token[0] == 0) 
			return false;
		if(token[0] == '#') 
			continue;
		if(!strcmp(token,"columns")) {
			in >> columns;
			eatAll();
		}
		if(!strcmp(token,"rows")) {
			in >> rows;
			eatAll();
		}
		if((columns != 0) && (rows != 0))
			return true;
	} while (true);

	return false;
}

private void getToken() {
	
	char c;
	while(true) {
		if(in.get(c) != NULL ) {	
			if(!isspace(c)) //BIALY ZNAK
				break;
		}
		else { //KONIEC PLIKU
			token[0] = 0;
			return;
		}
	}
	
	if( c == '#' ) 	{
		eatAll();
		token[0] = '#';
		return;
	}

	in.putback(c); // ZWROC CO POBRALES

	for(int i=0; i<size; i++) {
		if(!(in.get(c)) || isspace(c)) {
			token[i] = 0;					// KONIEC STRING-a
			break;
		}
		token[i] = c;
	}
}

private boolean loadWorld()
{

	Position p = new Position(0,0);

	char* type = new char[size];	
	char* name = new char[size];	
	char* direction = new char[size];
	char* fileName = new char[size];

	int data;
	int data2;

	//x y TYPE data data2 DIRECTION NAME
	do
	{	
		in >> p->x >> p->y;
		getToken();
		strcpy(type,token);
		in >> data;

		if(type[0] == 0) //przeniesc pod gettoken
			break;
		if(!strcmp(type,"ROBOT")) {
			in >> data2;
			getToken();
			strcpy(direction,token);
			getToken();
			strcpy(name,token);
			getToken();
			strcpy(fileName,"Intelligence\\");
			strcat(fileName,token);
			if(!loadObject(type,p,data,data2,direction,name,fileName))
				return false;
		}
		else {
			eatAll();
			loadObject(type,p,data);
		}

	} while (true);

	delete p;
	delete[] type;
	delete[] name;
	delete[] direction;
	delete[] fileName;
	return true;
}


private boolean loadObject(String type, Position p, int data, int data2, String direction, String name, File fileName)
{
	WorldObject worldObject;

	int typeOfWorldObject = WorldObjectVerifier.getWorldObjectByName(type);
	
	//CREATE ROBOT
	if ( typeOfWorldObject == WorldObjectVerifier.ROBOT.getIntValue()) {
		worldObject = new Robot(p,columns,rows,name,Direction.getDirectionByName(direction),data,data2,fileName);
		robot = worldObject;
	}
	//CREATE FLOOR
	else if( typeOfWorldObject == WorldObjectVerifier.FLOOR.getIntValue())
		worldObject = new Floor(p,data);
	//CREATE WALL
	else if( typeOfWorldObject == WorldObjectVerifier.WALL.getIntValue())
		worldObject = new Wall(p,data);
	//CREATE RUBBISH
	else if( typeOfWorldObject == WorldObjectVerifier.RUBBISH.getIntValue())
		worldObject = new Rubbish(p,data);
	//CREATE DEPOT
	else if( typeOfWorldObject == WorldObjectVerifier.DEPOT.getIntValue())
		worldObject = new Depot(p,data);
	//CREATE FURNITURE
	else if( typeOfWorldObject == WorldObjectVerifier.FURNITURE.getIntValue())
		worldObject = new Furniture(p,data);
	//ADD TO WORLD 
	if (!modelWorld.setCell(p,worldObject))
		return false;
	else
		return true;
}

private void eatAll()
{
	char c;
	while(in.get(c))
		if(c=='\n')
			break;
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