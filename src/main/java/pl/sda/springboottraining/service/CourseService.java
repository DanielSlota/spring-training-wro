package pl.sda.springboottraining.service;

import pl.sda.springboottraining.repository.model.Course;
import pl.sda.springboottraining.repository.model.Participant;

import java.util.List;
import java.util.Optional;

public interface CourseService {

    public List<Course> findAll(Integer minPrice, Integer maxPrice);

    void create(Course course);

    void update(Course course);

    void deleteById(Integer id);

    List<Participant> findParticipantsByCourseId(Integer id);

    Optional<Course> getById(Integer id);

    void assign(Integer id, Integer participantId);
}
