package com.portsadapter.example.effortestimation.domain.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import java.util.Objects;

public class Component {

    private UUID id;
    private String name;
    private Double effortEstimate;  // Nullable. Only leaves have effort.
    private Set<Component> subComponents = new HashSet<>();
    
    public Component(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
    }

    public Component(String name, double effortEstimate, Set<Component> subComponents) {
        this(name); // Call the other constructor to initialize common attributes.
        this.effortEstimate = effortEstimate;
        this.subComponents = subComponents;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getEffortEstimate() {
        return effortEstimate;
    }

    public Set<Component> getSubComponents() {
        return subComponents;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEffortEstimate(Double effortEstimate) {
        if (!subComponents.isEmpty() && effortEstimate != null) {
            throw new IllegalStateException("Cannot set effort estimate for a non-leaf component.");
        }
        this.effortEstimate = effortEstimate;
    }

    // Adds a sub-component to this component
    public void addSubComponent(Component component) {
        if (this.effortEstimate != null) {
            // If this was previously a leaf with an effort estimate, reset the effort estimate.
            this.effortEstimate = null;
        }
        subComponents.add(component);
    }
    
    // Removes a sub-component from this component
    public void removeSubComponent(Component component) {
        subComponents.remove(component);
    }
    

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Component component = (Component) o;
        return Objects.equals(id, component.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
