package pl.sda.springboottraining.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import pl.sda.springboottraining.repository.model.Participant;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public interface ParticipantDBRepository extends JpaRepository<Participant, Integer>, JpaSpecificationExecutor {

    Page<Participant> findByFirstName(String firstName, Pageable pageable);

    List<Participant> findByEmailAndFirstName(String email, String firstName, Sort sort);

    static Specification<Participant> hasName(String name) {
        return new Specification<Participant>() {
            @Override
            public Predicate toPredicate(Root<Participant> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("firstName"), name);
            }
        };
    }
}
