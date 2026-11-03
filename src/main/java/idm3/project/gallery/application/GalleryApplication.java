package idm3.project.gallery.application;

import idm3.project.gallery.repository.ProjectRepository;
import idm3.project.gallery.repository.ShowcaseRepository;
import idm3.project.gallery.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"idm3.project.gallery.service", "idm3.project.gallery.controllers"})
@EntityScan("idm3.project.gallery.model")
@EnableJpaRepositories("idm3.project.gallery.repository")

public class GalleryApplication implements CommandLineRunner {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ProjectRepository projectRepository;
	@Autowired
	private ShowcaseRepository showcaseRepository;

	private static final Logger log = LoggerFactory.getLogger(GalleryApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(GalleryApplication.class, args);
	}
	public void run(String... args) throws Exception {
		//fetch all projects
		log.info("all users found with findAll():");
		log.info("-------------------------------");
		userRepository.findAll().forEach(user -> {
			log.info(user.toString());
		});
		log.info("");
		//fetch all projects
		log.info("all projects found with findAll():");
		log.info("-------------------------------");
		projectRepository.findAll().forEach(project -> {
			log.info(project.toString());
		});
		log.info("");

//		//fetch all showcases
		log.info("all showcases found with findAll():");
		log.info("-------------------------------");
		showcaseRepository.findAll().forEach(showcase -> {
			log.info(showcase.toString());
		});
		log.info("");
	}
}
