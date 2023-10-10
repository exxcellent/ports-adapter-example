package com.portsadapter.example.effortestimation.domain.model;

import java.util.UUID;
import java.util.Objects;

public class Project {

    private UUID id;
    private String name;
    private String description;
    private Component rootComponent;

    public Project(String name, String description) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.description = description;
        this.rootComponent = new Component("Root");
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Component getRootComponent() {
        return rootComponent;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRootComponent(Component rootComponent) {
        this.rootComponent = rootComponent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return Objects.equals(id, project.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
