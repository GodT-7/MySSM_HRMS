package com.hrms.bean;

/**
 * @auther thk
 * @date 2020/11/23 - 17:22
 */
public class Collection {

    private Integer id;
    private Integer userId;
    private Integer sentenceId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSentenceId() {
        return sentenceId;
    }

    public void setSentenceId(Integer sentenceId) {
        this.sentenceId = sentenceId;
    }

    @Override
    public String toString() {
        return "Collection{" +
                "id=" + id +
                ", userId=" + userId +
                ", sentenceId=" + sentenceId +
                '}';
    }
}
