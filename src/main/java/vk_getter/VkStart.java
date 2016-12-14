package vk_getter;

import hiber_part.TripsEntity;
import hiber_part.hib;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.*;

/**
 * Created by sergey on 31.10.16.
 */
public class VkStart {

    public static List<WallPost> getWallList(){

        List<String> towns = new ArrayList<String>();

        towns.add( "-67095898" );

        List <WallPost> allPosts = new ArrayList<WallPost>();

        for ( String town : towns ){

            System.out.println("current_town = " + town);

            List townPost = VKScribe.getPosts( town );
            allPosts.addAll(townPost);
        }

        return allPosts;

    }

//    public static List<String> getTowns(String groupId ){
//
//        ArrayList towns = new ArrayList();
//
//        Map<String, List<String>> map = new Hashtable<String, List<String>>();
//
//        return null;
//
//    }
    public static void saveVkPostsToDb(){

        List<WallPost> wallPosts = VkStart.getWallList();
        List <TripsEntity> OrmPosts = new ArrayList<TripsEntity>();

        long lastTimeUpdate = hib.getLastDate();

        for (int i = 0; i < wallPosts.size(); i++) {

            WallPost wallPost = wallPosts.get( i );

            if (lastTimeUpdate < wallPost.getPostTime()){

                TripsEntity trip = wallPost.toTripsEntity();

                if ( trip != null ){
                    OrmPosts.add( wallPost.toTripsEntity() );

                }
            }
        }
        hib.savePost( OrmPosts );
    }

}
