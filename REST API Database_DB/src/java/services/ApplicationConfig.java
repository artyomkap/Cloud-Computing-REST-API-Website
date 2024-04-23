/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import java.util.Set;

/**
 *
 * @author T0322864
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends javax.ws.rs.core.Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(services.AcceptRequestResource.class);
        resources.add(services.AddTripResource.class);
        resources.add(services.CancelRequestResource.class);
        resources.add(services.DeleteRequestResource.class);
        resources.add(services.DeleteTripResource.class);
        resources.add(services.GetAllTripsResource.class);
        resources.add(services.GetRequestsResource.class);
        resources.add(services.GetTravelsByIdResource.class);
        resources.add(services.GetTripsByIdResource.class);
        resources.add(services.JoinToTripResource.class);
        resources.add(services.LoginResource.class);
        resources.add(services.RegistrationResource.class);
        resources.add(services.SelectUserResource.class);
        resources.add(services.UpdateFullNameResource.class);
        resources.add(services.UpdatePasswordResource.class);
        resources.add(services.UpdateUserNameResource.class);
    }
    
}
