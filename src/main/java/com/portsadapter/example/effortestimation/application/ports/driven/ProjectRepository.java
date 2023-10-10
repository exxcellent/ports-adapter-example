package com.portsadapter.example.effortestimation.application.ports.driven;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.portsadapter.example.effortestimation.domain.model.Project;

public interface ProjectRepository {
    Project save(Project project);

    List<Project> list();

    Optional<Project> findById(UUID id);

    void delete(Project project);
}
