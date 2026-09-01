package play.cine.cineplay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "play.cine")
public class CineplayApplication {

	public static void main(String[] args) {
		SpringApplication.run(CineplayApplication.class, args);
	}

}
