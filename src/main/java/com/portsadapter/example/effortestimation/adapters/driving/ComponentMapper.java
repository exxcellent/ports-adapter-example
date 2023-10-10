package com.portsadapter.example.effortestimation.adapters.driving;

import org.mapstruct.Mapper;

import com.portsadapter.example.effortestimation.domain.model.Component;

@Mapper(componentModel = "cdi")
public abstract class ComponentMapper {

    Component toDomainModel(ComponentInTO componentTO) {
        var component = new Component(componentTO.getName());
        component.setEffortEstimate(componentTO.getEffortEstimate());
        return component;
    }

}

