package vk_getter;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.utils.URIBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;



/**
 * Created by sergey on 11.10.16.
 */
public class VKScribe {

    public static List getPosts(String owner_id) {


        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder
                .setScheme("https")
                .setHost("api.vk.com")
                .setPath("/method/wall.get")
                .setParameter("owner_id", owner_id)
                .setParameter("count", "20");

        //String url = "https://api.vk.com/method/wall.get?owner_id=-67095898&count=10";
        //System.out.println( uriBuilder.getCharset() );
        HttpResponse response = HttpConnectionAgent.connectResponse(uriBuilder);
        //System.out.println( response.toString() );

        Integer status = response.getStatusLine().getStatusCode();

        List wallPosts = new ArrayList<WallPost>();

        if (status == 200) {
            StringWriter content = new StringWriter();

            try {
                IOUtils.copy(response.getEntity().getContent(), content);
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(-1);
            }

            JSONParser parser = new JSONParser();

            try {

                JSONObject jsonResp  = (JSONObject) parser.parse(content.toString());
                JSONArray  postsList = (JSONArray) jsonResp.get("response");
                JSONObject unicPost  = null;

                for (int i=1; i < postsList.size(); i++) {

                    unicPost = (JSONObject) postsList.get(i);
                    Long userId = new Long(unicPost.get("id").toString());
                    Long postTime = new Long(unicPost.get("date").toString());
                    String postText = unicPost.get("text").toString();
                    postText = postText.replaceAll( "<br>", " " );
                    WallPost post = new WallPost(userId, postTime, postText, owner_id);

                    wallPosts.add(post);
                }

            } catch (ParseException e) {
                e.printStackTrace();
                System.exit(-1);
            }
        }

        return wallPosts;
    }
}
