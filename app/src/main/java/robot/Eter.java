package robot;

import robot.legacy.List;

public class Eter extends List<Message> {

    public boolean sendMessage(Message message) {
        add(message);
        return true;
    }

    public Message retrieveMessage() {
        return getNext();
    }

    public void clearMessages() {
        Message message;
        this.setToFirst();
        while((message = retrieveMessage() ) != null) {
            this.remove(message);
        }
    }

}