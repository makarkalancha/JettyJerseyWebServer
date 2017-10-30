package webapp.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import webapp.pojos.Track;

/**
 * Created by Makar Kalancha
 * Date: 22 Nov 2016
 * Time: 14:02
 */
//http://localhost:9090/jetty-jsp-example/json/metallica/get
@Path("/json/metallica")
public class JSONService {

    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Track getTrackInJSON() {

        Track track = new Track();
        track.setTitle("Enter Sandman");
        track.setSinger("Metallica");

        return track;

    }

    /*
    {
        "singer":"Rammstein",
        "title":"Benzin"
    }
     */
    @POST
    @Path("/post")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createTrackInJSON(Track track) {

        String result = "Track saved : " + track;
        return Response.status(201).entity(result).build();

    }

}
