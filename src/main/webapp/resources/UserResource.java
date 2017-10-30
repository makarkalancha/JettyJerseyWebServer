/*
 * Software property of Acquisio. Copyright 2003-2017.
 */
package webapp.resources;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import org.apache.commons.lang.StringUtils;
import webapp.pojos.User;
import webapp.services.UserService;

/**
 * @author Macar Calancea (mcalancea@acquisio.com)
 */
/*
http://localhost:9090/jetty-jsp-example/user/1
http://localhost:9090/jetty-jsp-example/user/1?fields=userId
 */
@Path("/user")
public class UserResource {

    private UserService userService = new UserService();
    private final String DEFAULT_FIELDS = "id,name,title";
    @GET
    @Path("/id/{userId}")
    @Produces("application/json")
    public String getUser(@PathParam("userId") Long userId, @DefaultValue(DEFAULT_FIELDS) @QueryParam("fields") String fields){
        User user = userService.findOne(userId);

        if (StringUtils.isEmpty(fields)) {
            fields = DEFAULT_FIELDS;
        }
        StringTokenizer stringTokenizer = new StringTokenizer(fields, ",");
        Set<String> filterProperties = new HashSet<String>();
        while (stringTokenizer.hasMoreElements()){
            filterProperties.add(stringTokenizer.nextToken());
        }

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, true);
        objectMapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
        FilterProvider filterProvider =
                new SimpleFilterProvider().addFilter("userFilter", SimpleBeanPropertyFilter.filterOutAllExcept(filterProperties));
        try{
            String json = objectMapper.writer(filterProvider).writeValueAsString(user);
            return json;
        }catch (IOException e){
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
