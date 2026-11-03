package idm3.project.gallery.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

// Adds the build label to every page so the shared footer can show it
@ControllerAdvice
public class BuildInfoAdvice {

    @Value("${app.build-info:local dev}")
    private String buildInfo;

    @ModelAttribute("buildInfo")
    public String buildInfo() {
        return buildInfo;
    }
}