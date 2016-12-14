package vk_getter;

import hiber_part.TripsEntity;
import hiber_part.hib;
import post_parser.PostParser;

import java.util.*;

import static hiber_part.hib.getAssociations;

/**
 * Created by sergey on 04.10.16.
 */
public class WallPost {

    private Long userId;
    private Long postTime;
    private String postText;
    private String groupId;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getPostText() {
        return postText;
    }
    public void setPostText(String s) {
        postText = s.toLowerCase();
    }
    public Long getPostTime() {
        return postTime;
    }
    public Long getUserId() {
        return userId;
    }

    WallPost(Long userId, Long postTime, String postText,String groupId ){

        this.postText = postText.toLowerCase();
        this.postTime = postTime;
        this.userId = userId;
        this.groupId = groupId;

    }

    public boolean equals(WallPost post ){
        return ( post.getPostText().equals( this.postText ) && ( post.getUserId() == this.userId ) &&  ( post.getPostTime() == this.postTime ) );
    }

    public TripsEntity toTripsEntity( ){

        TripsEntity post = PostParser.tripJsnToOrm( this );
        return post;

    }

}


