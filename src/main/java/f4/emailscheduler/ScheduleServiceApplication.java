package f4.emailscheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@EnableFeignClients
public class ScheduleServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(ScheduleServiceApplication.class, args);
  }
}
