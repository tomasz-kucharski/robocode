package robotgame.object.opengl;

import robotgame.loader.TextureLoader;
import robotgame.object.Furniture;
import robotgame.object.WorldObject;

import javax.media.opengl.GL;

public class FurnitureGL extends ObjectGL {
    private int list2;
    private int list1;
    private int list3;

    public FurnitureGL() {
	list = 40;
	list1 = 41;
	list2 = 42;
	list3 = 43;
}

public void draw(GL gl, WorldObject object)
{
    Furniture furniture = (Furniture) object;
	gl.glPushMatrix();
    float mx = furniture.getDirection().getRotation();
	gl.glRotatef(mx,0.0f,0.0f,1.0f);

    gl.glBindTexture(GL.GL_TEXTURE_2D, TextureLoader.array[TextureLoader.FURNITURE]);

	switch (furniture.getType()) {
	case 0: gl.glCallList(list);
			break;
	case 1: gl.glCallList(list1);
			break;
	case 2: gl.glCallList(list2);
			break;
	case 3: gl.glCallList(list3);
			break;
	}
	gl.glPopMatrix();
}

public void init(GL gl)
{

    gl.glPushMatrix();
    gl.glNewList(list,GL.GL_COMPILE);
    CubeGL.createCube(gl,0.8f,true);
    gl.glEndList();
    gl.glPopMatrix();

	gl.glNewList(list,GL.GL_COMPILE);
//		#include "furniture"
	gl.glEndList();
	gl.glNewList(list1,GL.GL_COMPILE);
//		#include "furniture2"
	gl.glEndList();
	gl.glNewList(list2,GL.GL_COMPILE);
//		#include "furniture3"
	gl.glEndList();
	gl.glNewList(list3,GL.GL_COMPILE);
//		#include "furniture4"
	gl.glEndList();
}
}