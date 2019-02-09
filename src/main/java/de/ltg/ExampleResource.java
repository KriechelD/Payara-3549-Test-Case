package de.ltg;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/example")
public class ExampleResource extends JaxRsConfiguration {

    @GET
    public Response get() throws InterruptedException {
        return Response.ok("OK").build();
    }
}