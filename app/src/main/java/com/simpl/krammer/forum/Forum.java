package com.simpl.krammer.forum;

import java.util.ArrayList;

public class Forum {
    private String id;
    private String Forum_title;
    private String Forum_desc;
    private ArrayList<String> replies;//Reply--UserID

    public Forum(){

    }

    public Forum(String Forum_title, String forum_desc) {
        this.Forum_title = Forum_title;
        Forum_desc = forum_desc;
    }

    public Forum(String id, String Forum_title, String forum_desc) {
        this.id = id;
        this.Forum_title = Forum_title;
        Forum_desc = forum_desc;
    }

    public Forum(String id, String Forum_title, String forum_desc, ArrayList<String> replies) {
        this.id = id;
        this.Forum_title = Forum_title;
        Forum_desc = forum_desc;
        this.replies = replies;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getForum_title() {
        return Forum_title;
    }

    public void setForum_title(String Forum_title) {
        this.Forum_title = Forum_title;
    }

    public String getForum_desc() {
        return Forum_desc;
    }

    public void setForum_desc(String forum_desc) {
        Forum_desc = forum_desc;
    }

    public ArrayList<String> getReplies() {
        return replies;
    }

    public void setReplies(ArrayList<String> replies){
        this.replies=replies;
    }

    public void addReply(String reply){
        this.replies.add(reply);
    }

    public void removeReply(String reply){
        this.replies.remove(reply);
    }

    public void removeReply(int replyindex){
        this.replies.remove(replyindex);
    }
}
