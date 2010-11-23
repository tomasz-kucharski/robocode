package robotgame.opengl.loader.obj;

import java.util.*;

/**
 * @author tomekk
 * @since 2010-11-16, 22:30:24
 */
public class OBJModel {

    private Map<String,OBJGroup> groups = new HashMap<String,OBJGroup>();
    private OBJGroup currentGroup;

    public void addVertex(float vertexX, float vertexY, float vertexZ) {
        if (currentGroup != null) {
            currentGroup.addVertex(vertexX,vertexY, vertexZ);
        }
    }

    public void checkCurrentGroupAndInitializeDefaultGroup() {
        if (currentGroup == null) {
            currentGroup = new OBJGroup();
            groups.put(null,currentGroup);
        }
    }
}
