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


    private Point mousePoint;

    private JPopupMenu popupMenu = new PopUpDemo();

    private void init() {
        GLCapabilities caps = new GLCapabilities();
        caps.setDoubleBuffered(true);
        caps.setDepthBits(256);
        caps.setHardwareAccelerated(true);

        GLCanvas canvas = new GLCanvas(caps);
        canvas.addGLEventListener(this);
        canvas.addKeyListener(this);

        frame.add(canvas, BorderLayout.CENTER);


        frame.setSize(640, 480);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        frame.add(popupMenu);
        canvas.addMouseWheelListener(this);
        canvas.addMouseMotionListener(this);
        canvas.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                } else {
                mousePoint = e.getPoint();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });


        Animator anim = new Animator(canvas);
        anim.start();
        canvas.requestFocus();
    }

    public void loadWorld(File file) throws IOException {
        worldService = new WorldService(new BufferedReader(new FileReader(file)));
    }

    public void init(GLAutoDrawable gLDrawable) {
        gLDrawable.setAutoSwapBufferMode(true);
        GL gl = gLDrawable.getGL();
        worldService.setGL(gl);
        worldService.onInit(frame.getWidth(),frame.getHeight());
    }

    public void display(GLAutoDrawable gLDrawable) {
        final GL gl = gLDrawable.getGL();
        worldService.setGL(gl);
        worldService.onDraw();
    }

    public void reshape(GLAutoDrawable gLDrawable, int x, int y, int width, int height) {
        worldService.onResize(width,height);
    }

    @Override
    public void displayChanged(GLAutoDrawable glAutoDrawable, boolean b, boolean b1) {
    }

    public static void main(String[] args) throws IOException {
        SwingMain swingMain = new SwingMain();
        swingMain.loadWorld(new File("d:\\home\\projects\\robot\\app\\src\\main\\resources\\maps\\smallMap.txt"));
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
        worldService.onZMove(-e.getUnitsToScroll()/10f);
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


    class PopUpDemo extends JPopupMenu {

        public PopUpDemo(){
            final JCheckBoxMenuItem wireframe= new JCheckBoxMenuItem("Wireframe");
            wireframe.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    worldService.onWireframe(wireframe.isSelected());
                }
            });
            add(wireframe);
            final JCheckBoxMenuItem antyaliasing= new JCheckBoxMenuItem("Antialiasing");
            antyaliasing.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    worldService.onAntialiasing(antyaliasing.isSelected());
                }
            });
            add(antyaliasing);
            addSeparator();
            final JCheckBoxMenuItem evolve = new JCheckBoxMenuItem("Start");
            evolve.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    worldService.setEvolve(evolve.isSelected());
                }
            });
            add(evolve);
            final JCheckBoxMenuItem robotView = new JCheckBoxMenuItem("RobotView");
            robotView.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    worldService.OnRobotview(robotView.isSelected());
                }
            });
            add(robotView);
        }
    }

}
