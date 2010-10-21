import com.sun.opengl.util.Animator;
import robot.WorldService;

import javax.media.opengl.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class SwingMain implements GLEventListener, KeyListener, MouseWheelListener, MouseMotionListener {

    private Frame frame = new Frame("Robot Application");

    private WorldService worldService;
    private Animator anim;


    private Point mousePoint;

    private void init() {
        GLCapabilities caps = new GLCapabilities();
        caps.setDoubleBuffered(true);
        caps.setDepthBits(256);
        caps.setHardwareAccelerated(true);

        GLCanvas canvas = new GLCanvas(caps);
        canvas.addGLEventListener(this);
        canvas.addKeyListener(this);

        frame.add(canvas, BorderLayout.CENTER);


        createCheckBoxes();


        frame.setSize(640, 480);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
              System.exit(0);
            }
          });
        canvas.addMouseWheelListener(this);
        canvas.addMouseMotionListener(this);
        canvas.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                mousePoint = e.getPoint();
            }
        });


        anim = new Animator(canvas);
        anim.start();
        canvas.requestFocus();
    }

    private void createCheckBoxes() {
        final Checkbox checkboxAntyaliasing = new Checkbox();
        checkboxAntyaliasing.setLabel("Antialiasing");
        checkboxAntyaliasing.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                worldService.onAntialiasing(checkboxAntyaliasing.getState());
            }
        });
        final Checkbox checkboxWireframe = new Checkbox();
        checkboxWireframe.setLabel("Wireframe");
        checkboxWireframe.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                worldService.onWireframe(checkboxWireframe.getState());
            }
        });
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(checkboxAntyaliasing);
        panel.add(checkboxWireframe);
        frame.add(panel,BorderLayout.NORTH);
    }

    public void loadWorld(File file) throws IOException {
        worldService = new WorldService(new BufferedReader(new FileReader(file)));
    }

    public void init(GLAutoDrawable gLDrawable) {
        gLDrawable.setAutoSwapBufferMode(true);
        GL gl = gLDrawable.getGL();




        worldService.setGL(gl);
        worldService.onInit(640,480);
//        worldService.onWireframe(true);
//        worldService.onResize(640,480);

//        GLU glu = new GLU();
//
//              gl.glViewport(0,0,width,height);
//        gl.glMatrixMode(GL2.GL_PROJECTION);
//        gl.glLoadIdentity();
//        glu.gluPerspective(45.0f,(float)width/(float)height,0.1f,100.0f);
//        gl.glMatrixMode(GL2.GL_MODELVIEW);
//        gl.glLoadIdentity();
//        gl.glDrawBuffer(GL.GL_BACK);
    }

    public void display(GLAutoDrawable gLDrawable) {
//        final GLCapabilities glCababilities = gLDrawable.getChosenGLCapabilities();
//        glCababilities.setDoubleBuffered(true);
        final GL gl = gLDrawable.getGL();
        worldService.setGL(gl);
        worldService.onDraw();
//        worldService.evolve();


    }

    public void reshape(GLAutoDrawable gLDrawable, int x, int y, int width, int height) {
        worldService.onResize(width,height);
    }

    @Override
    public void displayChanged(GLAutoDrawable glAutoDrawable, boolean b, boolean b1) {
 }

    public void dispose(GLAutoDrawable gLDrawable) {
        // do nothing
    }

    public static void main(String[] args) throws IOException {
        SwingMain swingMain = new SwingMain();
        swingMain.loadWorld(new File("d:\\home\\projects\\robot\\app\\src\\main\\resources\\maps\\robot1.txt"));
        swingMain.init();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("keyTyped:"+e.getKeyChar());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            worldService.onXMove(-0.1f);
        }
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            worldService.onXMove(0.1f);
        }
        else if (e.getKeyCode() == KeyEvent.VK_UP) {
            worldService.onYMove(0.1f);
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            worldService.onYMove(-0.1f);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        worldService.onZMove(e.getUnitsToScroll()/10f);
    }

    
    @Override
    public void mouseDragged(MouseEvent e) {
        Point newPoint = e.getPoint();
        worldService.onXRotate((float) (mousePoint.x - newPoint.x));
        worldService.onYRotate((float) (mousePoint.y - newPoint.y));
        mousePoint = newPoint;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
}
