package com.bezkoder.springjwt.models;

import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity

public class Data {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "Date",nullable = false)
    private Date date;
    @Column(name = "Source", nullable = false)
    private String Source;
    @Column(name = "Emotion",nullable = false)
    private String Emotion;
    @Column(name = "Topic",nullable = false)
    private String Topic;
    @Column(name = "Commentaire")
    private String commentaire;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getSource() {
        return Source;
    }

    public void setSource(String source) {
        Source = source;
    }

    public String getEmotion() {
        return Emotion;
    }

    public void setEmotion(String emotion) {
        Emotion = emotion;
    }

    public String getTopic() {
        return Topic;
    }

    public void setTopic(String topic) {
        Topic = topic;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
