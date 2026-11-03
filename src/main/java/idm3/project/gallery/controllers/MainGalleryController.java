package idm3.project.gallery.controllers;


import idm3.project.gallery.model.Project;
import idm3.project.gallery.model.Showcase;
import idm3.project.gallery.model.User;
import idm3.project.gallery.service.ShowcaseService;
import idm3.project.gallery.service.ProjectService;
import idm3.project.gallery.service.UserService;
import idm3.project.gallery.service.ThumbnailService;
import jakarta.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller()
@RequestMapping(value = {"/MainGallery"})
public class MainGalleryController {

    @Autowired
    private UserService userService;
    @Autowired
    private ShowcaseService showcaseService;
    @Autowired
    private ProjectService projectService;

    @Autowired
    private ServletContext servletContext;
    @Autowired
    private ThumbnailService thumbnailService;
    // Display Login Page
    // Display Login Page
    @GetMapping("/Login")
    public ModelAndView showLoginPage() {
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    // Handle Login Submission
    @PostMapping("/Login")
    public ModelAndView handleLogin(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();
        User authenticatedUser = userService.authenticate(user.getUserName(), user.getPassword());
        if (authenticatedUser != null) {
            modelAndView.setViewName("redirect:/dashboard");
            modelAndView.addObject("username", authenticatedUser.getUserName());
        } else {
            modelAndView.setViewName("login");
            modelAndView.addObject("error", "Invalid username or password");
        }
        return modelAndView;
    }

    // Display Registration Page
    @GetMapping("/Register")
    public ModelAndView showRegisterPage() {
        ModelAndView modelAndView = new ModelAndView("register");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    // Handle Registration Submission
    @PostMapping("/Register")
    public ModelAndView handleRegister(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();
        user.setUserType("STUDENT");
        if (userService.registerUser(user)) {
            modelAndView.setViewName("redirect:/MainGallery/Login");
            modelAndView.addObject("message", "Registration successful! Please log in.");
        } else {
            modelAndView.setViewName("register");
            modelAndView.addObject("error", "Registration failed. Username or email might already exist.");
        }
        return modelAndView;
    }
    @RequestMapping(value = {"/HomePage", ""})
    public ModelAndView ModelAndViewsetUpIndexPageData() {
        System.out.println("ModelAndViewsetUpIndexPageData");
        ModelAndView mav = new ModelAndView("homepage");
        // find all projects
        List<Project> allProjects = projectService.findAll();

        generateThumbnailProject(allProjects);
        List<Showcase> allShowcases = generateThumbnailShowcases();

        mav.addObject("AllProjectsRecentFirst", allProjects);
        mav.addObject("AllLiveShowcases", allShowcases);
        return mav;


    }

    private List<Showcase> generateThumbnailShowcases() {
        List<Showcase> allShowcases = showcaseService.findAll();
        try{

        String imageDirPathShowcase = "src/main/resources/static/assets/images/showcases/";
        String thumbnailDirPathShowcase = "src/main/resources/static/assets/images/showcases/thumbnail/";
        for(Showcase showcase : allShowcases) {

            System.out.println(imageDirPathShowcase + showcase.getImage());
            File image = new File(imageDirPathShowcase + "/" +showcase.getImage());
            System.out.println("thumbnail:" + thumbnailDirPathShowcase + "thumb_" + image.getName());
            File thumbnailFile = new File(thumbnailDirPathShowcase + "/" + "thumb_" + image.getName());
            thumbnailService.generateThumbnailShowcase(image, thumbnailFile);
            System.out.println("Image uploaded and thumbnail created: " + thumbnailFile.getAbsolutePath());
        }
    } catch (IOException e) {
        e.printStackTrace();
        System.out.println("Failed to upload image or create thumbnail.");
    }
        return allShowcases;
    }

    private void generateThumbnailProject(List<Project> allProjects) {
        // Generate thumbnail
        try{
           String imageDirPathProject = "src/main/resources/static/assets/images/projects/";
           String thumbnailDirPathProject = "src/main/resources/static/assets/images/projects/thumbnail/";

        for(Project project : allProjects) {

            System.out.println(imageDirPathProject + project.getProjectHeroImage());
            File image = new File(imageDirPathProject + "/" +project.getProjectHeroImage());
            System.out.println("thumbnail:" + thumbnailDirPathProject + "thumb_" + image.getName());
            File thumbnailFile = new File(thumbnailDirPathProject + "/" + "thumb_" + image.getName());
            thumbnailService.generateThumbnail(image, thumbnailFile);
            System.out.println("Image uploaded and thumbnail created: " + thumbnailFile.getAbsolutePath());
        }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to upload image or create thumbnail.");
        }
    }

}