package robotgame.world;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Eter {

    List<Message> list = new ArrayList<Message>();

    public boolean sendMessage(Message message) {
        list.add(message);
        return true;
    }

    public Iterator retrieveMessage() {
        return list.iterator();
    }

    public void clearMessages() {
        list.clear();
    }

}