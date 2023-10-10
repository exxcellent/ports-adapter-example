package com.portsadapter.example.effortestimation.application.ports.driving;

import com.portsadapter.example.effortestimation.domain.model.Component;
import com.portsadapter.example.effortestimation.domain.model.Project;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface ProjectEstimationService {

    List<Project> getProjects();

    Project createProject(String projectName, String projectDescription);

    Project setRootComponentProject(UUID projectId, UUID componentId);

    Optional<Project> getProject(UUID projectId);

    Component createComponent(Component component);

    Component addSubcomponent(UUID componentId, UUID subComponentId);

    Component setEffortForComponent(UUID componentId, Double effort);

    Map<UUID, Double> getProjectEffortSummary(UUID projectId);
}
