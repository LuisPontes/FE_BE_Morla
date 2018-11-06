package pt.morla;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages={"pt.morla.*", "pt.morla.bo.db.interfaces.ISeparadores"})
@EnableJpaRepositories(basePackageClasses= {pt.morla.bo.db.interfaces.ISeparadores.class ,pt.morla.bo.db.interfaces.SeparadorRepository.class})
public class ProjectMorlaFrontEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectMorlaFrontEndApplication.class, args);
		
	}
}
