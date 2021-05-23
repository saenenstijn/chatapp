package controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import domain.Topic;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;


@ServerEndpoint(value = "/Blog")
public class Blog {
    //alle sessies
   private static final Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());
    //alle topics
   private static final List<Topic> topics = new ArrayList<>();

    //sessie toevoegen en blog bericht sturen
   @OnOpen
   public void onOpen(Session session) throws IOException, EncodeException{
       sessions.add(session);
       ObjectMapper mapper = new ObjectMapper();
       session.getBasicRemote().sendText(mapper.writeValueAsString(topics));
   }
    //topic lezen uit message en naar iedereen comment sturen en toevoegen in list
   @OnMessage
    public void onMessage(Session session, String topic) throws IOException, EncodeException{
       ObjectMapper mapper = new ObjectMapper();
       Topic topicObject = mapper.readValue(topic, Topic.class);
       sendCommentToAll(topicObject);
       topics.add(topicObject);
   }
    //sessie sluiten
   @OnClose
    public void onClose(Session session){sessions.remove(session);}

    //elke sessie overlopen en daar de berichten naar sturen
    private void sendCommentToAll(Topic topic) throws IOException, EncodeException{
       for(Session s : sessions){
           List<Topic> result = new ArrayList<>();
           result.add(topic);
           ObjectMapper mapper = new ObjectMapper();
           s.getBasicRemote().sendText(mapper.writeValueAsString(result));
       }
    }

}
