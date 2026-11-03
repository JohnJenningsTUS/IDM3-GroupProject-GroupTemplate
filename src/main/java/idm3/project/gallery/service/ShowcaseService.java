package idm3.project.gallery.service;

import idm3.project.gallery.model.Project;
import idm3.project.gallery.model.Showcase;
import idm3.project.gallery.repository.ProjectRepository;
import idm3.project.gallery.repository.ShowcaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ShowcaseService {

    @Autowired
    private ShowcaseRepository showcaseRepository;
    public List<Showcase> findAll() {
        return (List<Showcase>) showcaseRepository.findAll();
    }
}



