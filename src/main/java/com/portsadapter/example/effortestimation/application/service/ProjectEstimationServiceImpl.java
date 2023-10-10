package com.portsadapter.example.effortestimation.application.service;

import com.portsadapter.example.effortestimation.application.ports.driven.ComponentRepository;
import com.portsadapter.example.effortestimation.application.ports.driven.ProjectRepository;
import com.portsadapter.example.effortestimation.application.ports.driving.ProjectEstimationService;
import com.portsadapter.example.effortestimation.domain.model.Component;
import com.portsadapter.example.effortestimation.domain.model.Project;
import com.portsadapter.example.effortestimation.domain.service.EffortCalculationService;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class ProjectEstimationServiceImpl implements ProjectEstimationService {

    private final ProjectRepository projectRepository;
    private final ComponentRepository componentRepository;
    private final EffortCalculationService effortCalculationService;

    public ProjectEstimationServiceImpl(ProjectRepository projectRepository,
            ComponentRepository componentRepository,
            EffortCalculationService effortCalculationService) {
        this.projectRepository = projectRepository;
        this.componentRepository = componentRepository;
        this.effortCalculationService = effortCalculationService;
    }

    @Override
    public Project createProject(String projectName, String projectDescription) {
        Project newProject = new Project(projectName, projectDescription);
        projectRepository.save(newProject);
        return newProject;
    }

    @Override
    public Project setRootComponentProject(UUID projectId, UUID componentId) {
        var project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project not found!"));
        var component = componentRepository.findById(componentId)
                .orElseThrow(() -> new IllegalArgumentException("Component not found!"));
        project.setRootComponent(component);
        projectRepository.save(project);
        return project;
    }

    @Override
    public List<Project> getProjects() {
        return projectRepository.list();
    }

    @Override
    public Optional<Project> getProject(UUID projectId) {
        return projectRepository.findById(projectId);
    }

    @Override
    public Component createComponent(Component component) {
        componentRepository.save(component);
        return component;
    }

    @Override
    public Component addSubcomponent(UUID componentId, UUID subComponentId) {
        var component = componentRepository.findById(componentId)
                .orElseThrow(() -> new IllegalArgumentException("Component not found!"));
        var subComponent = componentRepository.findById(subComponentId)
                .orElseThrow(() -> new IllegalArgumentException("Sub-component not found!"));
        component.addSubComponent(subComponent);
        componentRepository.save(component);
        return component;
    }

    @Override
    public Component setEffortForComponent(UUID componentId, Double effortEstimate) {
        Component component = componentRepository.findById(componentId)
                .orElseThrow(() -> new IllegalArgumentException("Component not found!"));
        component.setEffortEstimate(effortEstimate);
        componentRepository.save(component);
        return component;
    }

    @Override
    public Map<UUID, Double> getProjectEffortSummary(UUID projectId) {
        return effortCalculationService.calculateEffort(projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project not found!")).getRootComponent());
    }

}
