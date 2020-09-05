package pl.sda.springboottraining.service;

import org.springframework.stereotype.Service;
import pl.sda.springboottraining.repository.DummyParticipantRepository;
import pl.sda.springboottraining.repository.model.Participant;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class DummyParticipantService implements ParticipantService {
    
    private final DummyParticipantRepository dummyParticipantRepository;

    public DummyParticipantService(DummyParticipantRepository dummyParticipantRepository) {
        this.dummyParticipantRepository = dummyParticipantRepository;
    }

    @Override
    public List<Participant> findAll() {
        return dummyParticipantRepository.findAll();
    }

    @Override
    public Optional<Participant> getByID(Integer id) {
        return dummyParticipantRepository.findById(id);
    }

    @Override
    public Integer save(Participant participant) {
        return dummyParticipantRepository.create(participant);
    }

    @Override
    public void saveAll(Collection<Participant> participantCollection) {
    }

    @Override
    public void update(Participant participant) {
        dummyParticipantRepository.update(participant.getId(), participant);
    }

    @Override
    public void deleteByID(Integer id) {
        dummyParticipantRepository.delete(id);
    }

    @Override
    public List<Participant> findAll(Integer page, Integer size, String name) {
        return null;
    }
}
