package pt.morla.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages={"pt.morla.app.*"})
@EnableJpaRepositories(basePackageClasses= {pt.morla.app.bo.db.interfaces.CategoriasRepository.class,pt.morla.app.bo.db.interfaces.ProjectosRepository.class,pt.morla.app.bo.db.interfaces.ImagesRepository.class})
@EnableCaching
public class MorlaAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MorlaAppApplication.class, args);
	}
}
