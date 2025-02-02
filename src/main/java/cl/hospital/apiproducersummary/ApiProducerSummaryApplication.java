package cl.hospital.apiproducersummary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ApiProducerSummaryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiProducerSummaryApplication.class, args);
	}

}
