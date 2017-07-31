package context.rest;

/**
 * Created by david on 2017/07/31.
 */
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;

import application.service.SampleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApplicationPath("rest")
@Api(description = "Services to help with test tracing")
@Path("/")
public class ContextRest extends Application {
    @GET
    @Path("/context")
    @ApiOperation(value = "set the test context",
            notes = "sets the test context so that sequences that are written out can be identified")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "successful operation") })
    public String context(@QueryParam("test-name") String testName) {
        System.setProperty("test.context", testName);
        return "test context set to '" + testName + "'";
    }
}
