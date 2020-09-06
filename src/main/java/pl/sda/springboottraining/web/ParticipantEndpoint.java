package pl.sda.springboottraining.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import pl.sda.springboottraining.repository.model.Participant;
import pl.sda.springboottraining.service.ParticipantService;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/participant")
public class ParticipantEndpoint {

    private final ParticipantService participantService;

    @Autowired
    public ParticipantEndpoint(@Qualifier("participantDBService")
                                       ParticipantService participantService) {
        this.participantService = participantService;
    }

//    @GetMapping
//    public List<Participant> getAll() {
//        return participantService.findAll();
//    }

    @GetMapping
    public List<Participant> getAllPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                        @RequestParam(value = "size", defaultValue = "10") Integer size,
                                        @RequestParam(value = "name", required = false) String name){
        return participantService.findAll(page, size, name);
    }

    @PostMapping("/list")
    public void saveAll(@RequestBody List<Participant> participants){
        participantService.saveAll(participants);
    }

    // /participant/1
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Integer id) throws URISyntaxException {
        Optional<Participant> participant = participantService.getByID(id);
        //zwroc status 200 jesli kursant istnieje, lub 404 gdy nie istnieje
        return participant.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)//zawsze zwracaj status 201 gdy dodanie
    //sie powiodlo
    public Integer create(@RequestBody @Valid Participant participant) {
        return participantService.save(participant);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Integer id,
                       @RequestBody @Valid Participant participant) {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        participantService.update(participant);
    }

    // /participant?participantId=1
    @DeleteMapping
    public void delete(@RequestParam("participantId") Integer id) {
        participantService.deleteByID(id);
    }
}
