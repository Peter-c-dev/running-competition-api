package org.example.runcomp.repository;

import org.example.runcomp.model.Run;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RunRepository extends JpaRepository<Run, Long> {

    // ⭐ REQUIRED for getRunsForUser() to work
    List<Run> findByUserId(Long userId);
}
