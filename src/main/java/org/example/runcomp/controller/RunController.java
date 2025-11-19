package org.example.runcomp.controller;

import org.example.runcomp.model.Run;
import org.example.runcomp.repository.RunRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/runs")
public class RunController {

    @Autowired
    private RunRepository runRepository;

    // Add a new run
    @PostMapping
    public ResponseEntity<Run> addRun(@RequestBody Run run) {
        Run savedRun = runRepository.save(run);
        return ResponseEntity.ok(savedRun);
    }

    // Get all runs
    @GetMapping
    public List<Run> getAllRuns() {
        return runRepository.findAll();
    }

    // Calculate pace
    @GetMapping("/pace")
    public double calculatePace(
            @RequestParam double distance,
            @RequestParam double time
    ) {
        if (time <= 0) {
            throw new IllegalArgumentException("Time must be greater than zero");
        }

        return time / distance;
    }
}

