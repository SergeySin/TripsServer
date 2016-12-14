package spring_part;

import hiber_part.hib;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by sergey on 15.11.16.
 */
@Component
public class Scheduler {

    @Scheduled(fixedRate = 1000 * 60 * 60 )
    public void executeSavePostsToDataBase() {

        System.out.println( "Update DataBase" );
        hib.startCicle();
    }

}
