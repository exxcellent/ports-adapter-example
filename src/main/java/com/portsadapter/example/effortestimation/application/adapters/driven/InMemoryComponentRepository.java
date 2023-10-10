package com.portsadapter.example.effortestimation.application.adapters.driven;

import com.portsadapter.example.effortestimation.application.ports.driven.ComponentRepository;
import com.portsadapter.example.effortestimation.domain.model.Component;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class InMemoryComponentRepository implements ComponentRepository {

    private final Map<UUID, Component> components = new HashMap<>();

    @Override
    public Component save(Component component) {
        components.put(component.getId(), component);
        return component;
    }

    @Override
    public Optional<Component> findById(UUID id) {
        return Optional.ofNullable(components.get(id));
    }

    @Override
    public void delete(Component component) {
        components.remove(component.getId());
    }
}