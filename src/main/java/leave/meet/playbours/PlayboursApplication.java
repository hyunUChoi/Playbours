package leave.meet.playbours;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class PlayboursApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlayboursApplication.class, args);
    }

}
