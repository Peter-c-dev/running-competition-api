package org.example.runcomp.dto;

public record CreateRunRequest(
        Long userId,
        double distance,
        double time,
        String location
) {}
