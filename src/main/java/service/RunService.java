package org.example.runcomp.service;

import org.example.runcomp.dto.CreateRunRequest;
import org.example.runcomp.dto.RunDTO;
import org.example.runcomp.model.Run;
import org.example.runcomp.model.User;
import org.example.runcomp.repository.RunRepository;
import org.example.runcomp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RunService {

    private final RunRepository runRepository;
    private final UserRepository userRepository;

    public RunService(RunRepository runRepository, UserRepository userRepository) {
        this.runRepository = runRepository;
        this.userRepository = userRepository;
    }

    // ---------------------------------------------------------
    // CREATE RUN
    // ---------------------------------------------------------
    public RunDTO createRun(CreateRunRequest request) {

        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Run run = new Run();
        run.setDistance(request.distance());
        run.setTime(request.time());
        run.setLocation(request.location());
        run.setUser(user);

        Run saved = runRepository.save(run);

        return new RunDTO(
                saved.getId(),
                saved.getDistance(),
                saved.getTime(),
                null,                          // no pace yet
                saved.getUser().getId(),
                saved.getUser().getName(),
                saved.getLocation()
        );
    }

    // ---------------------------------------------------------
    // GET ALL RUNS
    // ---------------------------------------------------------
    public List<RunDTO> getAllRuns() {
        return runRepository.findAll().stream()
                .map(r -> new RunDTO(
                        r.getId(),
                        r.getDistance(),
                        r.getTime(),
                        null,                      // no pace yet
                        r.getUser() != null ? r.getUser().getId() : null,
                        r.getUser() != null ? r.getUser().getName() : null,
                        r.getLocation()
                ))
                .toList();
    }

    // ---------------------------------------------------------
    // GET ALL RUNS FOR ONE USER
    // ---------------------------------------------------------
    public List<RunDTO> getRunsForUser(Long userId) {
        return runRepository.findByUserId(userId).stream()
                .map(r -> new RunDTO(
                        r.getId(),
                        r.getDistance(),
                        r.getTime(),
                        null,                     // no pace yet
                        r.getUser().getId(),
                        r.getUser().getName(),
                        r.getLocation()
                ))
                .toList();
    }
}
