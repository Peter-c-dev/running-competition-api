package org.example.runcomp.dto;

public record RunDTO(
        Long id,
        double distance,
        double time,
        Double pace,
        Long userId,
        String userName,
        String location
) {}

