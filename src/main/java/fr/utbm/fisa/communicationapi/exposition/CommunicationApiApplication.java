package fr.utbm.fisa.communicationapi.exposition;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("fr.utbm.fisa.communicationapi.*")
@EnableJpaRepositories("fr.utbm.fisa.communicationapi.*")
@EntityScan("fr.utbm.fisa.communicationapi.*")
public class CommunicationApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommunicationApiApplication.class, args);
	}

}
