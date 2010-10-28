package robotgame.object;

import robotgame.loader.TextureLoader;

import javax.media.opengl.GL;

public class FurnitureGL implements WorldObjectRenderer {


    private int list4 = 40;
    private int list2 = 41;
    private int list1 = 42;
    private int list3 = 43;

    private GL gl;

    @Override
    public void setGraphicsContext(Object context) {
        gl = (GL) context;
    }

    @Override
    public void init() {
        gl.glPushMatrix();
        gl.glNewList(list4,GL.GL_COMPILE);
        CubeGL.createCube(gl,0.8f,true);
        gl.glEndList();
        gl.glPopMatrix();

//        gl.glNewList(list4,GL.GL_COMPILE);
////		#include "furniture"
//        gl.glEndList();
//        gl.glNewList(list1,GL.GL_COMPILE);
////		#include "furniture2"
//        gl.glEndList();
//        gl.glNewList(list2,GL.GL_COMPILE);
////		#include "furniture3"
//        gl.glEndList();
//        gl.glNewList(list3,GL.GL_COMPILE);
////		#include "furniture4"
//        gl.glEndList();
    }

    @Override
    public void draw(WorldObject object) {
        Furniture furniture = (Furniture)object;

        gl.glPushMatrix();
        float mx = furniture.getDirection().getRotation();
        gl.glRotatef(mx,0.0f,0.0f,1.0f);

        gl.glBindTexture(GL.GL_TEXTURE_2D, TextureLoader.array[TextureLoader.FURNITURE]);

//        switch (furniture.getType()) {
//        case 0: gl.glCallList(list4);
//                break;
//        case 1: gl.glCallList(list1);
//                break;
//        case 2: gl.glCallList(list2);
//                break;
//        case 3: gl.glCallList(list3);
//                break;
//        }
        gl.glPopMatrix();
    }
}