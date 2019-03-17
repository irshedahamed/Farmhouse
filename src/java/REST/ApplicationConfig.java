/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package REST;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author irshed
 */
@javax.ws.rs.ApplicationPath("Rest")
public class ApplicationConfig extends Application {

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
        resources.add(REST.AuthenticateResource.class);
        resources.add(REST.FetchDisplayResource.class);
        resources.add(REST.FetchFarmByIdResource.class);
        resources.add(REST.FetchItemResource.class);
        resources.add(REST.FetchUserByIdResource.class);
        resources.add(REST.GetItemResource.class);
        resources.add(REST.InsertFarmerResource.class);
        resources.add(REST.InsertItemResource.class);
        resources.add(REST.InsertUserResource.class);
        resources.add(REST.SetReviewResource.class);
    }
    
}
