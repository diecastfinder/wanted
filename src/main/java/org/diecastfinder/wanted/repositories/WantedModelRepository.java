package org.diecastfinder.wanted.repositories;

import org.diecastfinder.wanted.domain.WantedModel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WantedModelRepository extends JpaRepository<WantedModel, UUID> {
    List<WantedModel> findByActiveTrue();
}
