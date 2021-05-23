package domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Topic {

    //voor blog

    private String topic;
    private int rating;
    private int topicID;
    private String name;

    //json properties zijn belangrijk voor genereren van json
    @JsonCreator
    public Topic (@JsonProperty("topicID") int topicID, @JsonProperty("feedback") String topic, @JsonProperty("rating") int rating, @JsonProperty("name") String name){
        setRating(rating);
        setTopic(topic);
        setName(name);
        setTopicID(topicID);
    }

    public Topic(){}

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        if(rating > 10 || rating < 0){
            throw new DomainException("Rating niet tussen 0-10");
        }
        this.rating = rating;
    }

    public int getTopicID() {
        return topicID;
    }

    public void setTopicID(int topicID) {
        this.topicID = topicID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
