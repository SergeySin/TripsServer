package hiber_part;

import org.hibernate.Query;
import org.hibernate.Session;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import vk_getter.VkStart;

import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

/**
 * Created by sergey on 28.10.16.
 */

public class hib {

    public static JSONObject response = new JSONObject();
    public static JSONObject getResponse() {
        return response;
    }

    public static void createJsonForService( )
    {
//        System.out.println( "Hello World!" );
//        System.out.println("Hibernate tutorial");
        Session session = HibernateSessionFactory.getSessionFactory().openSession();

//        session.beginTransaction();

//        TripsEntity contactEntity = new TripsEntity();

        JSONArray postsArr = new JSONArray();

        List<TripsEntity> posts = (List<TripsEntity>)session.createQuery("from TripsEntity order by tripsTime").list();

        for(Iterator<TripsEntity> it = posts.iterator(); it.hasNext(); ) {

            JSONObject post = it.next().toJson();
            postsArr.add( post );

        }

        response.put("response", postsArr);

//        session.save(contactEntity);
//        session.getTransaction().commit();

        session.close();
    }

    public static void savePost( List<TripsEntity> allOrmPosts )
    {
        System.out.println("savePost");


        //TripsEntity contactEntity = new TripsEntity();

        for (int i = 0; i < allOrmPosts.size() - 1; i++) {
            Session session = HibernateSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();
            TripsEntity contactEntity = allOrmPosts.get( i );
            session.save( contactEntity );
            session.getTransaction().commit();
//            System.out.println( "nene" + i );
            session.close();
        }
        //contactEntity.setTripsUserComment( "sdsdsdsdsdsd" );
        //session.save( contactEntity );

    }
    public static void startCicle(){

        getLastDate();
        deleteNullTrips();
        deleteOldTrips();
        VkStart.saveVkPostsToDb();
        createJsonForService();


    }

    public static void deleteNullTrips(){

        System.out.println( "deleteOldTrips!" );
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        Query query =  session.createQuery("delete TripsEntity where tripsPostDate = :param");

        query.setParameter("param", null);
        int result = query.executeUpdate();

        System.out.println( result + "   result" );

        session.getTransaction().commit();
        session.close();

    }

    public static void deleteOldTrips(){

        System.out.println( "deleteOldTrips!" );

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        Calendar calendar = Calendar.getInstance();


//        (from TripsEntity  where  tripsPostDate < :param)

        String queryExp = "DELETE TripsEntity where tripsDate < :pastTripsDate";

        Query query =  session.createQuery(queryExp);

        int days_ago = -1;
        calendar.add(Calendar.DATE, -2);
        Date pastTripsDate =  new Date(calendar.getTime().getTime());
        query.setParameter( "pastTripsDate", pastTripsDate );
        int result = query.executeUpdate();

        System.out.println( pastTripsDate.toString() + "   pastTripsDate" );

        System.out.println( result + "   result pastTripsDate" );

        session.getTransaction().commit();

        session.close();

    }    public static void deleteOldPost(){

        System.out.println( "deleteOldPost!" );

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        Calendar calendar = Calendar.getInstance();

        String queryExp = "DELETE TripsEntity where tripsPostDate < :pastTripsPostDate";

        Query query =  session.createQuery(queryExp);

        int days_ago = -2;
        calendar.add(Calendar.DATE, -2);
        Date pastTripsPostDate =  new Date(calendar.getTime().getTime());
        query.setParameter( "pastTripsPostDate", pastTripsPostDate );
        int result = query.executeUpdate();

        System.out.println( pastTripsPostDate.toString() + "   pastTripsPostDate" );

        System.out.println( result + " result pastTripsPostDate" );

        session.getTransaction().commit();

        session.close();

    }

    public static long getLastDate(){

        System.out.println("getLastDate");
        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        JSONArray postsArr = new JSONArray();

        String query = "from TripsEntity where tripsPostTime = (select max (tripsPostTime) from TripsEntity where tripsPostDate = ( select max (tripsPostDate) from TripsEntity ))";

        List<TripsEntity> posts = (List<TripsEntity>)session.createQuery(query).list();
        session.close();

        System.out.println( posts.size() + "  posts.size ");

//        if ( posts.size() > 0 ){
//
//            Time lastPostTime = posts.get(0).getTripsPostTime();
//            Date lastPostDate = posts.get(0).getTripsDate();
//            return lastPostTime.getTime() + lastPostDate.getTime();
//
//        }
        return 0;
    }

    public static List<String> getAssociations(String s){

//        System.out.println("getAssociations");
        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        JSONArray postsArr = new JSONArray();

        String stringQuery = "select saName from SourceAssociationsEntity where saSourceId = ( SELECT sourceId from SourceEntity where sourceName =:param )";

        Query query = session.createQuery(stringQuery);

        query.setParameter( "param", s );

//        query.setParameter(s,param);

        List<String> associations = (List<String>) query.list();
        session.close();

//        System.out.println( associations.size() + "  associations.size ");

        return associations ;
    }

}
