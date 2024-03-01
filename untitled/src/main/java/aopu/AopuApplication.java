package aopu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@SpringBootApplication()
public class AopuApplication {
    public static void main(String[] args) {
        SpringApplication.run(AopuApplication.class,args);
    }
}
