package robotgame.object;

/**
 * @author tomekk
 * @since 2010-10-27, 21:41:44
 */
public interface WorldObjectRenderer {

    void setGraphicsContext(Object context);
    
    void init();

    void draw(WorldObject object);
}
