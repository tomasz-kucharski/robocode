package robotgame.loader.obj;

import java.util.*;

/**
 * @author tomekk
 * @since 2010-11-16, 22:30:24
 */
public class OBJModel {

    private Map<String,OBJGroup> groups = new HashMap<String,OBJGroup>();
    private OBJGroup currentGroup;

    public OBJGroup getCurrentGroup() {
        checkCurrentGroupAndInitializeDefaultGroup();
        return currentGroup;
    }

    public void checkCurrentGroupAndInitializeDefaultGroup() {
        if (currentGroup == null) {
            currentGroup = new OBJGroup();
            groups.put(null,currentGroup);
        }
    }

    public Map<String, OBJGroup> getGroups() {
        return groups;
    }
}
