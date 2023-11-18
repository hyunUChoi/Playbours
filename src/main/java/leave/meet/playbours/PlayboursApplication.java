package leave.meet.playbours;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class PlayboursApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlayboursApplication.class, args);
    }

}
