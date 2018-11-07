package pt.morla.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages={"pt.morla.app.*"})
@EnableJpaRepositories(basePackageClasses= {pt.morla.app.bo.db.interfaces.ISeparadores.class ,pt.morla.app.bo.db.interfaces.SeparadorRepository.class})
public class MorlaAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MorlaAppApplication.class, args);
	}
}
