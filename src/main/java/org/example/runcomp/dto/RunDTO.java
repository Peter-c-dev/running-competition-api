package org.example.runcomp.dto;

public record RunDTO(
        Long id,
        double distance,
        double time,
        double pace,
        Long userId
) {}
