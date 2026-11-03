package idm3.project.gallery.repository;

import idm3.project.gallery.model.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {
}

