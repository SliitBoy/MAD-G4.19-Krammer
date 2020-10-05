package com.simpl.krammer.forum;

import java.util.ArrayList;

public class Forum {

    private String forum_title;
    private String forum_desc;
   // private ArrayList<String> replies;//Reply--UserID

    public Forum(){

    }

    public Forum(String Forum_title, String Forum_desc) {
        this.forum_title = Forum_title;
        this.forum_desc = Forum_desc;
    }


//    public Forum(String Forum_title, String Forum_desc, ArrayList<String> replies) {
//        this.forum_title = Forum_title;
//        this.forum_desc = Forum_desc;
//        this.replies = replies;
//    }

    public String getForum_title() {
        return forum_title;
    }

    public void setForum_title(String forum_title) {
        this.forum_title = forum_title;
    }

    public String getForum_desc() {
        return forum_desc;
    }

    public void setForum_desc(String forum_desc) {
        this.forum_desc = forum_desc;
    }

//    public ArrayList<String> getReplies() {
//        return replies;
//    }
//
//    public void setReplies(ArrayList<String> replies) {
//        this.replies = replies;
//    }
}
