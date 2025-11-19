package service;


import org.example.runcomp.dto.CreateRunRequest;
import org.example.runcomp.dto.RunDTO;
import org.example.runcomp.model.Run;
import org.example.runcomp.model.User;
import org.example.runcomp.repository.RunRepository;
import org.example.runcomp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RunService {

    @Autowired
    private RunRepository runRepository;

    @Autowired
    private UserRepository userRepository;

    public RunDTO createRun(CreateRunRequest request) {

        // Find the user or throw an exception
        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Create the run
        Run run = new Run();
        run.setDistance(request.distance());
        run.setTime(request.time());
        run.setUser(user);

        // Save it
        Run saved = runRepository.save(run);

        return new RunDTO(
                saved.getId(),
                saved.getDistance(),
                saved.getTime(),
                saved.getTime() / saved.getDistance(),
                saved.getUser().getId()
        );
    }
    public List<RunDTO> getAllRuns() {
        return runRepository.findAll().stream()
                .map(r -> new RunDTO(
                        r.getId(),
                        r.getDistance(),
                        r.getTime(),
                        r.getTime() / r.getDistance(),
                        r.getUser() != null ? r.getUser().getId() : null
                ))
                .toList();
    }

}
