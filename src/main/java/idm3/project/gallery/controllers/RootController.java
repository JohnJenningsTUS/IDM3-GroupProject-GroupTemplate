package idm3.project.gallery.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {

    // Send the site root to the gallery home page
    @GetMapping("/")
    public String root() {
        return "redirect:/MainGallery/HomePage";
    }
}