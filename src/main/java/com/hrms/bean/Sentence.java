package com.hrms.bean;

/**
 * @auther thk
 * @date 2020/11/22 - 10:34
 */
public class Sentence {

    private Integer id;
    private String sentence;
    private String author;
    private Integer userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Sentence{" +
                "id=" + id +
                ", snetence='" + sentence + '\'' +
                ", auther='" + author + '\'' +
                ", userId=" + userId +
                '}';
    }
}
