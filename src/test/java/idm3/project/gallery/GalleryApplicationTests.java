package idm3.project.gallery;

import idm3.project.gallery.application.GalleryApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

// GalleryApplication lives in a sub-package (idm3.project.gallery.application),
// so @SpringBootTest can't find it on its own and must be told which class to start.
@SpringBootTest(classes = GalleryApplication.class)
class GalleryApplicationTests {

	@Test
	void contextLoads() {
	}

}