package mx.edu.utez.cursos.service;

import mx.edu.utez.cursos.model.Course;
import mx.edu.utez.cursos.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private CourseRepository repo;

    public CourseService(CourseRepository repo) {
        this.repo = repo;
    }

    public List<Course> getAll() {
        return repo.findAll();
    }

    public Optional<Course> findById(Long id) {
        return repo.findById(id);
    }

    public Course save(Course course) {
        return repo.save(course);
    }

    public boolean delete(Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<Course> update(Long id, Course course) {
        Optional<Course> optional = repo.findById(id);
        if (optional.isEmpty()) {
            return Optional.empty();
        }
        Course courseDb = optional.get();
        courseDb.setName(course.getName());
        courseDb.setHours(course.getHours());
        courseDb.setPrice(course.getPrice());
        return Optional.of(repo.save(courseDb));
    }
}