package robotgame;

import com.sun.opengl.util.Animator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import robotgame.loader.DeployWorld;
import robotgame.loader.FileTextureLoader;
import robotgame.opengl.WorldServiceOpenGL;
import robotgame.world.WorldGL;

import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL10Impl;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class SwingMain implements GLEventListener, KeyListener, MouseWheelListener, MouseMotionListener {
    private static final Logger log = LoggerFactory.getLogger(SwingMain.class);

    private Point mousePoint;
    private JPopupMenu popupMenu = new PopUpDemo();

    private WorldService worldService;
    private JFrame frame = new JFrame(ROBOT_APPLICATION);
    private GL10 gl10;

    private long numFrames;
    private long fpsStartTime;
    public static final String ROBOT_APPLICATION = "Robot Application";

    public SwingMain(String contextPath) {
        fpsStartTime = System.currentTimeMillis();
        worldService = new WorldServiceOpenGL(new WorldGL());
        worldService.setTextureLoader(new FileTextureLoader(contextPath));
    }

    private void init() {
        GLCapabilities caps = new GLCapabilities();
        caps.setDoubleBuffered(true);
        caps.setDepthBits(256);
        caps.setHardwareAccelerated(true);


        GLCanvas canvas = new GLCanvas(caps);
        canvas.addGLEventListener(this);
        canvas.addKeyListener(this);
        frame.add(canvas);

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

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        frame.setSize(1024, 768);
        frame.setVisible(true);
        frame.add(popupMenu);

        Animator anim = new Animator(canvas);
        anim.start();
        canvas.requestFocus();
    }

    public void loadWorld(String contextPath, File file) throws IOException {
        DeployWorld deployWorld = new DeployWorld(new BufferedReader(new FileReader(file)));
        deployWorld.setContextPath(contextPath);
        worldService.onMapLoad(deployWorld.loadWorld());
        worldService.setMainRobot(deployWorld.getRobot());
    }

    public void init(GLAutoDrawable gLDrawable) {
        gl10 = new GL10Impl(gLDrawable.getGL());
//        gLDrawable.setGL(new TraceGL(gLDrawable.getGL(),System.out));
        worldService.setGraphicsContext(gl10);
        worldService.onInit(frame.getWidth(),frame.getHeight());
    }

    public void display(GLAutoDrawable gLDrawable) {
        worldService.onDraw();
        logTime();
    }

    private void logTime() {
        numFrames++;
        long fpsElapsed = System.currentTimeMillis() - fpsStartTime;
        if (fpsElapsed > 1000) { 
            float fps = (numFrames * 1000.0F) / fpsElapsed;
            fpsStartTime = System.currentTimeMillis();
            numFrames = 0;
            frame.setTitle(ROBOT_APPLICATION+" ("+fps+" fps)");
        }
    }

    public void reshape(GLAutoDrawable gLDrawable, int x, int y, int width, int height) {
        worldService.onResize(width,height);
        System.out.println("onResize:"+width+", "+height);
    }

    @Override
    public void displayChanged(GLAutoDrawable glAutoDrawable, boolean b, boolean b1) {
        System.out.println("b1"+b1);
    }

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            log.warn("You must provide scenario location as a first argument");
            System.exit(0);
        }
        SwingMain swingMain = new SwingMain(args[0]);
        swingMain.loadWorld(args[0], new File(args[0]+"\\maps\\smallMap.txt"));
        swingMain.init();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("keyTyped:"+e.getKeyChar());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            worldService.getConfiguration().changeMoveX(0.1f);
        }
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            worldService.getConfiguration().changeMoveX(-0.1f);
        }
        else if (e.getKeyCode() == KeyEvent.VK_UP) {
            worldService.getConfiguration().changeMoveY(-0.1f);
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            worldService.getConfiguration().changeMoveY(0.1f);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        worldService.getConfiguration().changeMoveZ(-e.getUnitsToScroll()/10f);
    }


    @Override
    public void mouseDragged(MouseEvent e) {
        Point newPoint = e.getPoint();
        worldService.getConfiguration().changeRotateX((float) (mousePoint.x - newPoint.x));
        worldService.getConfiguration().changeRotateY((float) (mousePoint.y - newPoint.y));
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
                    worldService.getConfiguration().setWireframe(wireframe.isSelected());
                }
            });
            add(wireframe);

            final JCheckBoxMenuItem antyaliasing= new JCheckBoxMenuItem("Antialiasing");
            antyaliasing.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    worldService.getConfiguration().setAntialiasing(antyaliasing.isSelected());
                }
            });
            add(antyaliasing);

            final JCheckBoxMenuItem light= new JCheckBoxMenuItem("Light");
            light.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    worldService.getConfiguration().setLight(light.isSelected());
                }
            });
            add(light);

            addSeparator();

            final JCheckBoxMenuItem evolve = new JCheckBoxMenuItem("Start");
            evolve.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    worldService.getConfiguration().setEvolve(evolve.isSelected());
                }
            });
            add(evolve);

            final JCheckBoxMenuItem robotView = new JCheckBoxMenuItem("RobotView");
            robotView.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    worldService.getConfiguration().setRobotView(robotView.isSelected());
                }
            });
            add(robotView);
        }
    }
}
