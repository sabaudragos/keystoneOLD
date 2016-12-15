package keystone.rest;

import keystone.domain.SensorPlatform;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static keystone.rest.RestConstants.SENSOR_DATA_PATH;

@Path(SENSOR_DATA_PATH)
public class SensorDataResource {
    @POST
    public Response acquireData() {

        return Response.ok().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDataToBeDisplayed(@DefaultValue("") @QueryParam("temperature") String temperature,
                                         @DefaultValue("") @QueryParam("temperature") String pressure) {


        SensorPlatform sensorPlatform = new SensorPlatform();
        sensorPlatform.setId(1);

        return Response.ok().entity(sensorPlatform).build();
    }
}
