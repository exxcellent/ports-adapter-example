package com.portsadapter.example.effortestimation.application.ports.driven;

import java.util.Optional;
import java.util.UUID;

import com.portsadapter.example.effortestimation.domain.model.Component;

public interface ComponentRepository {
    Component save(Component component);

    Optional<Component> findById(UUID id);

    void delete(Component component);
}
