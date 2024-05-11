package api.englishAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@SpringBootApplication
public class EnglishApiApplication {

	public static void main(String[] args) {

		String port = System.getenv("PORT");
		if (port == null || port.isEmpty()) {
			port = "8080";
		}


		SpringApplication app = new SpringApplication(EnglishApiApplication.class);
		app.setDefaultProperties(Collections.singletonMap("server.port", port));
		app.run(args);
	}


}
