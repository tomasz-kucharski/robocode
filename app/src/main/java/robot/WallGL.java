package robot;

import robot.object.WorldObject;
import robot.object.opengl.ObjectGL;

import javax.media.opengl.GL;

public class WallGL extends ObjectGL {
    public WallGL()
    {
        list = 70;
    }

    public void draw(GL gl, WorldObject object) {
        gl.glCallList(list);
    }

    public void init(GL gl) {
//this->loadGLTextures("wall.bmp");
        gl.glNewList(list,GL.GL_COMPILE);

/*		glColor3f(0.5f,0.5f,0.5f);
	glBindTexture(GL_TEXTURE_2D,texture[0]);
	glBegin(GL_QUADS);
	//gora				
		glNormal3f(0.0f,0.0f,1.0f);
		//	glColor3f(0.8f,0.1f,0.1f);
			glTexCoord2f(0.0f,0.0f);	glVertex3f(-0.5f,-0.5f,1.0f);
		//	glColor3f(0.8f,0.05f,0.05f);
			glTexCoord2f(0.0f,1.0f);	glVertex3f(-0.5f,0.5f,1.0f);
		//	glColor3f(0.75f,0.15f,0.15f);
			glTexCoord2f(1.0f,1.0f);	glVertex3f(0.5f,0.5f,1.0f);
		//	glColor3f(0.6f,0.0f,0.0f);	
			glTexCoord2f(1.0f,0.0f);	glVertex3f(0.5f,-0.5f,1.0f);
	//przod
		glNormal3f(0.0f,1.0f,0.0f);
		//	glColor3f(0.5f,0.1f,0.1f);	
			glTexCoord2f(0.0f,0.0f);	glVertex3f(-0.5f,0.5f,0.0f);
		//	glColor3f(0.5f,0.1f,0.1f);	
			glTexCoord2f(1.0f,0.0f);	glVertex3f(0.5f,0.5f,0.0f);
		//	glColor3f(0.9f,0.2f,0.2f);	
			glTexCoord2f(1.0f,1.0f);	glVertex3f(0.5f,0.5f,1.0f);
		//	glColor3f(0.9f,0.2f,0.2f);	
			glTexCoord2f(0.0f,1.0f);	glVertex3f(-0.5f,0.5f,1.0f);
	//tyl
		glNormal3f(0.0f,-1.0f,0.0f);
		//	glColor3f(0.5f,0.1f,0.1f);	
			glTexCoord2f(0.0f,0.0f);	glVertex3f(-0.5f,-0.5f,0.0f);
		//	glColor3f(0.5f,0.1f,0.1f);	
			glTexCoord2f(1.0f,0.0f);	glVertex3f(0.5f,-0.5f,0.0f);
		//	glColor3f(0.9f,0.2f,0.2f);	
			glTexCoord2f(1.0f,1.0f);	glVertex3f(0.5f,-0.5f,1.0f);
		//	glColor3f(0.9f,0.2f,0.2f);	
			glTexCoord2f(0.0f,1.0f);	glVertex3f(-0.5f,-0.5f,1.0f);
	//lewy
		glNormal3f(-1.0f,0.0f,0.0f);
		//	glColor3f(0.5f,0.1f,0.1f);	
			glTexCoord2f(0.0f,0.0f);	glVertex3f(-0.5f,-0.5f,0.0f);
		//	glColor3f(0.9f,0.2f,0.2f);	
			glTexCoord2f(0.0f,1.0f);	glVertex3f(-0.5f,-0.5f,1.0f);
		//	glColor3f(0.9f,0.2f,0.2f);	
			glTexCoord2f(1.0f,1.0f);	glVertex3f(-0.5f,0.5f,1.0f);
		//	glColor3f(0.5f,0.1f,0.1f);	
			glTexCoord2f(1.0f,0.0f);	glVertex3f(-0.5f,0.5f,0.0f);
	//prawy
		glNormal3f(1.0f,0.0f,0.0f);
		//	glColor3f(0.5f,0.1f,0.1f);	
			glTexCoord2f(0.0f,0.0f);	glVertex3f(0.5f,-0.5f,0.0f);
		//	glColor3f(0.9f,0.2f,0.2f);	
			glTexCoord2f(0.0f,1.0f);	glVertex3f(0.5f,-0.5f,1.0f);
		//	glColor3f(0.9f,0.2f,0.2f);	
			glTexCoord2f(1.0f,1.0f);	glVertex3f(0.5f,0.5f,1.0f);
		//	glColor3f(0.5f,0.1f,0.1f);	
			glTexCoord2f(1.0f,0.0f);	glVertex3f(0.5f,0.5f,0.0f);
	glEnd();
*/
//        #include "wall"
        gl.glEndList();
    }
}