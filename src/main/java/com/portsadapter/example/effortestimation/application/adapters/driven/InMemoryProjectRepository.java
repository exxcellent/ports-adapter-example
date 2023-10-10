package com.portsadapter.example.effortestimation.application.adapters.driven;

import com.portsadapter.example.effortestimation.application.ports.driven.ProjectRepository;
import com.portsadapter.example.effortestimation.domain.model.Project;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class InMemoryProjectRepository implements ProjectRepository {

    private final Map<UUID, Project> projects = new HashMap<>();

    @Override
    public Project save(Project project) {
        projects.put(project.getId(), project);
        return project;
    }

    @Override
    public List<Project> list() {
        return projects.values().stream().toList();
    }

    @Override
    public Optional<Project> findById(UUID id) {
        return Optional.ofNullable(projects.get(id));
    }

    @Override
    public void delete(Project project) {
        projects.remove(project.getId());
    }
}
