package com.portsadapter.example.effortestimation.adapters.driving;

import java.util.UUID;

import com.portsadapter.example.effortestimation.application.ports.driving.ProjectEstimationService;
import com.portsadapter.example.effortestimation.domain.model.Project;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProjectResource {

    @Inject
    ProjectEstimationService projectEstimationService;

    @Inject
    ComponentMapper componentMapper;

    @POST
    @Path("/projects")
    public Response createProject(ProjectInTO project) {
        Project createdProject = projectEstimationService.createProject(project.getName(), project.getDescription());
        return Response.status(Response.Status.CREATED).entity(createdProject).build();
    }

    @GET
    @Path("/projects")
    public Response getProjects() {
        return Response.ok(projectEstimationService.getProjects()).build();
    }

        @POST
    @Path("/projects/{projectId}")
    public Response setRootComponent(@PathParam("projectId") UUID projectId, UuidTO rootComponentId) {
        projectEstimationService.setRootComponentProject(projectId, rootComponentId.getId());
        return Response.status(Response.Status.CREATED).build();

    }

    @GET
    @Path("/projects/{projectId}")
    public Response getProject(@PathParam("projectId") UUID projectId) {
        return projectEstimationService.getProject(projectId)
                .map(project -> Response.ok(project).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    @Path("/components")
    public Response createComponent(ComponentInTO componentIn) {
        var component = projectEstimationService.createComponent(componentMapper.toDomainModel(componentIn));
        return Response.status(Response.Status.CREATED).entity(component).build();
    }

    @POST
    @Path("/components/{componentId}")
    public Response addSubcomponent(@PathParam("componentId") UUID componentId, UuidTO subcomponentId) {
        projectEstimationService.addSubcomponent(componentId, subcomponentId.getId());
        return Response.status(Response.Status.CREATED).build();

    }

    @GET
    @Path("/projects/{projectId}/effort-summary")
    public Response getProjectEstimationSummary(@PathParam("projectId") UUID projectId) {
        var effortSummary = projectEstimationService.getProjectEffortSummary(projectId);
        return Response.ok(effortSummary).build();
    }
}
