package post_parser;

import hiber_part.HibernateSessionFactory;
import hiber_part.TripsEntity;
import hiber_part.hib;
import org.hibernate.Query;
import org.hibernate.Session;
import org.json.simple.JSONArray;
import vk_getter.WallPost;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static hiber_part.hib.getAssociations;

/**
 * Created by sergey on 07.11.16.
 */

public class PostParser {

    public static void parsing(){

    }

    public static TripsEntity tripJsnToOrm(WallPost post){

        String postAllText = post.getPostText();

        if (PostParser.isTrip(postAllText)) {

            TripsEntity postEntity = new TripsEntity();

            postEntity.setTripsUserId(post.getUserId().toString());
            postEntity.setTripsUserPost(post.getPostText());

            postEntity.setTripsGroupId(post.getGroupId());

            Time time = new Time(post.getPostTime() * 1000L);
            Date date = new Date(post.getPostTime() * 1000L);

            postEntity.setTripsPostDate(date);
            postEntity.setTripsPostTime(time);

            postAllText = PostParser.delWasteSpaces(postAllText);
            postAllText = PostParser.delWasteSymb(postAllText);
            postAllText = PostParser.delWasteWords(postAllText);
            postAllText = PostParser.delWasteSpaces(postAllText);
            postAllText = PostParser.addMissingSpaces(postAllText);

            System.out.println( "Normalize text = " + postAllText );

            List<String> direction = getDirections( postAllText, post.getGroupId() );

            if ( direction != null ) {

                postEntity.setTripsFrom(direction.get(0));
                postEntity.setTripsTo(direction.get(1));

                System.out.println( postAllText );

                String stringTime = PostParser.getTime(postAllText);

                if ( stringTime != null ){

                try {

                    postEntity.setTripsTime(PostParser.parseTime(stringTime));

                }catch ( java.text.ParseException e ){

                    System.out.println( e );
                }

                postEntity.setTripsDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));

                return postEntity;

                }else{

                    System.out.println( " stringTime == null  " + postAllText );

                }

            }else{

                System.out.println( " direction == null  " + postAllText );
            }

        }else{
            System.out.println( " is_not_trip  " + postAllText );
        }

        return null;
    }

    public static void startParsingService(){

        while ( true ){

            System.out.println( "enter update" );
            Scanner sc = new Scanner(System.in);
            String s1 = sc.nextLine();

            System.out.println( "you enter ' " + s1  + " '  " );

            if ( s1.equals("update")  ) {

                System.out.println( "startCicle" );
                hib.startCicle();

            }else {

                normalizePostText(s1);

            }

        }
    }

    public static List<String> getTowns(String groupId ){

//        HashMap hashTowns = new HashMap();
        Map<String, ArrayList<String>> hashTowns = new HashMap<String, ArrayList<String>>();

        hashTowns.put("-67095898", new ArrayList<String>(Arrays.asList("пенза", "сердобск")));

        return hashTowns.get(groupId);

    }

    public static boolean isTrip(String s){

        List<String> associations = getAssociations( "is_not_trip" );

        for (int i=1; i < associations.size(); i++) {

            String word = associations.get(i);
            String postText = s.toLowerCase();
            if (postText.contains( word )){

                return false;

            }

        }
        return true;

    }

    public static List<String> getDirections(String s, String group){

        List<String> towns = getTowns(group);

        for (int i = 0; i < towns.size(); i++) {

            List<String> assTownNames = hib.getAssociations( towns.get(i) );

            assTownNames.add(towns.get(i));

            for (int j = 0; j < assTownNames.size(); j++) {

                String associationName = assTownNames.get(j);
                String postText = s;

                if ( postText.contains( associationName ) ){

                    if ( i > 0 ){

                        return new ArrayList<String>(Arrays.asList(towns.get(1), towns.get(0)));

                    }else{

                        return towns;

                    }

                }

            }

        }

        return null;

    }

    public static void normalizePostText(String postText){

        postText = postText.toLowerCase();

        List<String> arrayList = new ArrayList<String>(Arrays.asList(postText.split(" ")));

        System.out.println("normalizePostText");
        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        for (int j = 0; j < arrayList.size(); j++) {

            String curWord = getRecoveryWord(arrayList.get(j));

            String sourceQueryTxt = "from SourceEntity where sourceName =:curWord";
            Query sourceQuery =  session.createQuery(sourceQueryTxt);
            sourceQuery.setParameter( "curWord", curWord );
            //sourceQuery.executeUpdate();
            List<String> sourceMatches = (List<String>) sourceQuery.list();

            if ( sourceMatches.size() < 1 ){

                String sourceAssQueryTxt = "from SourceAssociationsEntity where saName =:curWord";
                Query sourceAssQuery =  session.createQuery(sourceAssQueryTxt);
                sourceAssQuery.setParameter( "curWord", curWord );
                //sourceAssQuery.executeUpdate();
                List<String> sourceAssMatches = (List<String>) sourceAssQuery.list();


                if ( sourceAssMatches.size() < 1 ){

                    System.out.println( "Need add word *" + curWord + "*" );

                }

            }

        }
        session.close();


    }

    public static String getTime(String postText){

        postText = postText.toLowerCase();

        List<String> arrayList = new ArrayList<String>(Arrays.asList(postText.split(" ")));

        System.out.println("getTime");


        for (int j = 0; j < arrayList.size(); j++) {

            String curWord = arrayList.get(j);

            if (PostParser.isTime(curWord)){

                return curWord;

            }

        }

        return null;

    }

    public static String getRecoveryWord(String word){

        int length = word.length();

        if ( length > 2 ){

            String startSym = word.charAt(0) + "";
            String endSym = word.charAt(length-1) + "";

            String chars = "[!@#$%^&*()_+.,]";

            startSym = startSym.replaceAll(chars, "");
            endSym = endSym.replaceAll(chars, "");
            word = startSym + word.substring(1, length-1) + endSym;
        }

        System.out.println( "String recovery *" + word + "*" );

    return word;

    }

    public static boolean isTime(String word){

        Pattern p = Pattern.compile("^(1?[0-9]|2[0-3])[.:-][0-5][0|5]$");
        Matcher m = p.matcher(word);
        return m.matches();

    }

    public static String addMissingSpaces(String post){

        Pattern pt = Pattern.compile("[а-я][0-9]|[0-9][а-я]]");
        Matcher mt = pt.matcher(post);

        while( mt.find() ){

            post = post.substring(0,mt.start()+1) + " " + post.substring(mt.start()+1,post.length());
            mt = pt.matcher(post);
        };

        return post;

    }

    public static String delWasteSymb(String post){

        return post.replaceAll("[\\!|,|(|)|\"|\\?|/]+", "");

    }

    public static String delWasteWords(String post){

        post = post.replaceAll("\\sв\\s", " ");

        post = post.replaceAll("\\sв\\s", " ");
        post = post.replaceAll("^в\\s", "");

        return post;

    }

    public static String delWastePoints(String post){

        Pattern pt = Pattern.compile("[а-я]\\.");
        Matcher mt = pt.matcher(post);

        while( mt.find() ){
            System.out.println(mt.start());
            System.out.println(mt.group());
            System.out.println("blabla.length() = " + post.length());
            post = post.substring(0,mt.start()+1) + post.substring(mt.start()+2,post.length());
            System.out.println(post);
            mt = pt.matcher(post);
        };

        pt = Pattern.compile("\\.[а-я]");
        mt = pt.matcher(post);

        while( mt.find() ){
            System.out.println(mt.start());
            System.out.println(mt.group());
            System.out.println("blabla.length() = " + post.length());
            post = post.substring(0,mt.start()) + post.substring(mt.start()+1,post.length());
            System.out.println(post);
            mt = pt.matcher(post);
        };

        return post;

    }
    public static String delWasteSpaces(String post){
        return post.replaceAll("[\\s]{2,}", " ");
    }

    public static Time parseTime(String stringTime) throws java.text.ParseException {

        int replaceSymPos = 2;

        if ( stringTime.length() == 4 ){

            stringTime = "0" + stringTime;

        }

        StringBuffer sb = new StringBuffer(stringTime);
        sb.setCharAt(replaceSymPos, ':');
        stringTime = sb.toString();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        long ms = sdf.parse(stringTime).getTime();
        Time t = new Time(ms);
        return t;

    }
}



