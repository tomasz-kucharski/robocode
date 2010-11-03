package robotgame;

import com.sun.opengl.util.Animator;
import robotgame.loader.DeployWorld;
import robotgame.loader.FileTextureLoader;
import robotgame.loader.TextureLoader;
import robotgame.object.*;
import robotgame.world.MapObject;
import robotgame.world.WorldGL;
import robotgame.world.WorldRenderer;

import javax.media.opengl.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class SwingMain implements GLEventListener, KeyListener, MouseWheelListener, MouseMotionListener {

    private Point mousePoint;
    private JPopupMenu popupMenu = new PopUpDemo();

    private WorldService worldService = new WorldService();
    private JFrame frame = new JFrame("Robot Application");


    public SwingMain(String contextPath) {

        HashMap<MapObject, WorldObjectRenderer> rendererHashMap = new HashMap<MapObject, WorldObjectRenderer>();
        rendererHashMap.put(MapObject.DEPOT,new DepotGL());
        rendererHashMap.put(MapObject.FURNITURE,new FurnitureGL());
        rendererHashMap.put(MapObject.RUBBISH,new RubbishGL());
        rendererHashMap.put(MapObject.ROBOT,new RobotGL());
        rendererHashMap.put(MapObject.FLOOR,new FloorGL());
        rendererHashMap.put(MapObject.WALL,new WallGL());

        WorldRenderer worldRenderer = new WorldGL();
        worldRenderer.setObjectRendererMap(rendererHashMap);
        worldService.setWorldRenderer(worldRenderer);


        FileTextureLoader fileTextureLoader = new FileTextureLoader();
        fileTextureLoader.setContextPath(contextPath);
        worldService.setTextureLoader(fileTextureLoader);
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
//        gLDrawable.setGL(new TraceGL(gLDrawable.getGL(),System.out));
        worldService.setGraphicsContext(gLDrawable.getGL());
        worldService.onInit(frame.getWidth(),frame.getHeight());
    }

    public void display(GLAutoDrawable gLDrawable) {
        worldService.setGraphicsContext(gLDrawable.getGL());
        worldService.onDraw();
    }

    public void reshape(GLAutoDrawable gLDrawable, int x, int y, int width, int height) {
        worldService.setGraphicsContext(gLDrawable.getGL());
        worldService.onResize(width,height);
        System.out.println("onResize:"+width+", "+height);
    }

    @Override
    public void displayChanged(GLAutoDrawable glAutoDrawable, boolean b, boolean b1) {
        System.out.println("b1"+b1);
    }

    public static void main(String[] args) throws IOException {
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
