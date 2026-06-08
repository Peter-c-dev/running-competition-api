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
    @GetMapping("/{id}")
    public ResponseEntity<Run> getRunById(@PathVariable Long id) {
        return runRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PutMapping("/{id}")
    public ResponseEntity<Run> updateRun
            (@PathVariable Long id,
             @RequestBody Run updatedRun) {
        return runRepository.findById(id)
                .map(run -> {
                    run.setDistance(updatedRun.getDistance());
                    run.setTime(updatedRun.getTime());
                    run.setLocation(updatedRun.getLocation());
                    return ResponseEntity.ok(
                            runRepository.save(run)
                    );
                })
                .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRun(@PathVariable Long id) {
        if(!runRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        runRepository.deleteById(id);
        return ResponseEntity.noContent().build();
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

