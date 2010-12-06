package robotgame.opengl.loader.obj;

/**
 * @author tomekk
 * @since 2010-12-03, 00:04:18
 */
public class OBJIndex {
    
    private Integer vertex;
    private Integer texture;
    private Integer normal;

    public int getVertex() {
        return vertex;
    }

    public void setVertex(int vertex) {
        this.vertex = vertex;
    }

    public int getTexture() {
        return texture;
    }

    public void setTexture(int texture) {
        this.texture = texture;
    }

    public int getNormal() {
        return normal;
    }

    public void setNormal(int normal) {
        this.normal = normal;
    }

    public OBJIndexType getType() {
        if (texture != null) {
            if (normal != null) {
                return OBJIndexType.VERTEX_TEXTURE_NORMAL;
            } else {
                return OBJIndexType.VERTEX_TEXTURE;
            }
        } else {
            if (normal != null) {
                return OBJIndexType.VERTEX_NORMAL;
            } else {
                return OBJIndexType.VERTEX;
            }
        }
    }
}