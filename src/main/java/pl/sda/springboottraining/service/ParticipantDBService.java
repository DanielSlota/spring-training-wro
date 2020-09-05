package pl.sda.springboottraining.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.sda.springboottraining.repository.ParticipantDBRepository;
import pl.sda.springboottraining.repository.model.Participant;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static pl.sda.springboottraining.repository.ParticipantDBRepository.hasName;

@Service
public class ParticipantDBService implements ParticipantService {

    private final ParticipantDBRepository participantDBRepository;

    public ParticipantDBService(ParticipantDBRepository participantDBRepository) {
        this.participantDBRepository = participantDBRepository;
    }

    @Override
    public List<Participant> findAll() {
        return participantDBRepository.findAll();
    }

    @Override
    public Optional<Participant> getByID(Integer id) {
        return participantDBRepository.findById(id);
    }

    @Override
    public Integer save(Participant participant) {
        return participantDBRepository.save(participant).getId();
    }

    @Override
    public void saveAll(Collection<Participant> participantCollection) {
        participantDBRepository.saveAll(participantCollection);
    }

    @Override
    public void update(Participant participant) {
        participantDBRepository.save(participant);
    }

    @Override
    public void deleteByID(Integer id) {
        participantDBRepository.deleteById(id);
    }

    @Override
    public List<Participant> findAll(Integer page, Integer size, String name) {
        Pageable pageable = PageRequest.of(page, size);
        if(name != null){
            return participantDBRepository.findAll(hasName(name),pageable).getContent();
        } else {
            return participantDBRepository.findAll(pageable).getContent();
        }
    }
}
