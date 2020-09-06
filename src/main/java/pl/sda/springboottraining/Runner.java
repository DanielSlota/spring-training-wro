package pl.sda.springboottraining;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import pl.sda.springboottraining.repository.ParticipantDBRepository;
import pl.sda.springboottraining.security.UserAccount;
import pl.sda.springboottraining.security.UserRepository;

@Component
public class Runner implements CommandLineRunner {

    private final ParticipantDBRepository participantDBRepository;

    private final UserRepository userRepository;

    public Runner(ParticipantDBRepository participantDBRepository, UserRepository userRepository) {
        this.participantDBRepository = participantDBRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {
//        Participant participant = new Participant();
//        participant.setIdNumber("1234567890");
//        participant.setFirstName("Jan");
//        participant.setLastName("Kowalski");
//        participant.setEmail("chri456pat1@gmail.com");

//        participantDBRepository.save(participant);

//        // username: user password: user
//        userRepository.save(new UserAccount("user",
//                "$2a$04$1.YhMIgNX/8TkCKGFUONWO1waedKhQ5KrnB30fl0Q01QKqmzLf.Zi",
//                "USER"));
//        // username: admin password: admin
//        userRepository.save(new UserAccount("admin",
//                "$2a$04$KNLUwOWHVQZVpXyMBNc7JOzbLiBjb9Tk9bP7KNcPI12ICuvzXQQKG",
//                "ADMIN"));

        if(userRepository.count() == 0) {

            userRepository.save(new UserAccount("lukasz",
                    new BCryptPasswordEncoder().encode("lukasz"),
                    "ADMIN"));
        }

    }
}
