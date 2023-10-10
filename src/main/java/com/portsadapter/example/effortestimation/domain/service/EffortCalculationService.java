package com.portsadapter.example.effortestimation.domain.service;

import com.portsadapter.example.effortestimation.domain.model.Component;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@ApplicationScoped
public class EffortCalculationService {

    public Map<UUID, Double> calculateEffort(Component rootComponent) {
        Map<UUID, Double> effortMap = new HashMap<>();
        calculateEffortRecursively(rootComponent, effortMap);
        return effortMap;
    }

    private Double calculateEffortRecursively(Component component, Map<UUID, Double> effortMap) {
        double totalEffort = component.getEffortEstimate() == null ? 0 : component.getEffortEstimate();

        for (Component child : component.getSubComponents()) {
            totalEffort += calculateEffortRecursively(child, effortMap);
        }

        effortMap.put(component.getId(), totalEffort);
        return totalEffort;
    }
}