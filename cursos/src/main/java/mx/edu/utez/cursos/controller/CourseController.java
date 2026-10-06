package mx.edu.utez.cursos.controller;

import mx.edu.utez.cursos.model.Course;
import mx.edu.utez.cursos.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<List<Course>> findAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getById(@PathVariable long id) {
        Optional<Course> course = service.findById(id);
        if (course.isPresent()) {
            return ResponseEntity.ok(course.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    public ResponseEntity<Course> postMethodName(@RequestBody Course course) {
        Course savedCourse = service.save(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCourse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Course> delete(@PathVariable Long id) {
        boolean deleted = service.delete(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> update(@PathVariable Long id, @RequestBody Course course) {
        Optional<Course> optional = service.update(id, course);
        if (optional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(optional.get());
        }
        return ResponseEntity.notFound().build();
    }
}