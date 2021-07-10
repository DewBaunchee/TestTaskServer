package by.vorivoda.matvey.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(exclude= HibernateJpaAutoConfiguration.class)
public class MonitorSensorsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonitorSensorsApplication.class, args);
    }
}