package domain;

import java.util.ArrayList;
import java.util.List;

public class Conversation {
    //2 personen
    public Person p1, p2;
    //lijst van berichten
    public List<String> messages;



    public Conversation(Person p1, Person p2){
        this.p1 = p1;
        this.p2 = p2;
        messages = new ArrayList<>();
    }
}
