package de.seniorenheim.speedify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpeedifyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpeedifyApplication.class, args);
    }

}
