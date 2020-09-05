package pl.sda.springboottraining.service;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import pl.sda.springboottraining.repository.CourseRepository;
import pl.sda.springboottraining.repository.ParticipantDBRepository;
import pl.sda.springboottraining.repository.model.Course;
import pl.sda.springboottraining.repository.model.Participant;
import pl.sda.springboottraining.service.filter.CourseFilter;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static pl.sda.springboottraining.repository.CourseRepository.*;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    private final ParticipantDBRepository participantDBRepository;

    private final EmailService emailService;

    public CourseServiceImpl(CourseRepository courseRepository,
                             ParticipantDBRepository participantDBRepository,
                             EmailService emailService) {
        this.courseRepository = courseRepository;
        this.participantDBRepository = participantDBRepository;
        this.emailService = emailService;
    }

    @Override
    public List<Course> findAll(CourseFilter courseFilter) {

        Specification<Course> specification = Specification.where(null);

        if (courseFilter.getMinPrice() != null) {
            specification = specification.and(hasPriceGreaterThan(courseFilter.getMinPrice()));
        }
        if (courseFilter.getMaxPrice() != null) {
            specification = specification.and(hasPriceLessThan(courseFilter.getMaxPrice()));
        }
        if (courseFilter.getMaxParticipants() != null){
            specification = specification.and(hasParticipantsLessThan(courseFilter.getMaxParticipants()));
        }
        if (courseFilter.getName() != null) {
            specification = specification.and(hasInName(courseFilter.getName()));

        }

        return courseRepository.findAll(specification);
    }

    @Override
    public void create(Course course) {
        courseRepository.save(course);
    }

    @Override
    public void update(Course course) {
        courseRepository.save(course);
    }

    @Override
    public void deleteById(Integer id) {
        courseRepository.deleteById(id);
    }

    @Override
    public List<Participant> findParticipantsByCourseId(Integer id) {
        return courseRepository
                .findById(id)
                .map(Course::getParticipants)
                .stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Course> getById(Integer id) {
        return courseRepository.findById(id);
    }

    @Override
    public void assign(Integer id, Integer participantId) {

        Course course = courseRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Participant participant = participantDBRepository
                .findById(participantId)
                .orElseThrow(() -> new RuntimeException("Participant not found"));

        course.addParticipant(participant);
        participant.addCourse(course);

        courseRepository.save(course);
        participantDBRepository.save(participant);

        emailService.sendSimpleMessage(participant.getEmail(),
                "Nowy kurs",
                "Zostałeś przypisany do kursu: " + course.getName());
    }
}
