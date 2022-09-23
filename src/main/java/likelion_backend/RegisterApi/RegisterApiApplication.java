package likelion_backend.RegisterApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@PropertySource("classpath:/env.properties")
public class RegisterApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegisterApiApplication.class, args);
	}

}
