package universidade;

import java.util.ArrayList;
import java.util.List;

public class Chat {
    private Student student;
    private Owner owner;
    private Property referenceProperty;
    private List<String> messages;

    public Chat(Student student, Owner owner, Property referenceProperty) {
        this.student = student;
        this.owner = owner;
        this.referenceProperty = referenceProperty;
        this.messages = new ArrayList<>();
    }

    public void addMessageAndNotify(String message) {
        this.messages.add(message);
    }

    public List<String> getMessageHistory() {
        return messages;
    }
}
