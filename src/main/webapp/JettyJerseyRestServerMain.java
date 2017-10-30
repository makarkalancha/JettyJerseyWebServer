package webapp;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ServerProperties;
import webapp.resources.JSONService;
import webapp.resources.UserResource;

/**
 * Created by mcalancea on 2016-03-08.
 */
public class JettyJerseyRestServerMain {

    public static void main(String[] args) throws Exception {

        // 1. Creating the server on port 8080
        Server server = new Server(9090);

        // 2. Creating the WebAppContext for the created content
        ServletContextHandler ctx = new ServletContextHandler(ServletContextHandler.SESSIONS);
//        ctx.setResourceBase("src/main/webapp");
//        ctx.setResourceBase("src/main");
        ctx.setContextPath("/jetty-jsp-example");

        ServletHolder servletHolder = ctx.addServlet(
                org.glassfish.jersey.servlet.ServletContainer.class, "/*");
        servletHolder.setInitOrder(0);

        // Tells the Jersey Servlet which REST service/class to load.
        servletHolder.setInitParameter(
//                "jersey.config.server.provider.classnames",
                ServerProperties.PROVIDER_CLASSNAMES,
                JSONService.class.getCanonicalName() + "," + UserResource.class.getCanonicalName());

        servletHolder.setInitParameter("com.sun.jersey.config.property.packages",
                "webapp.services.rest");

        //5. Setting the handler and starting the Server
        try {
            server.setHandler(ctx);
            server.start();
            server.join();
        }finally {
            server.destroy();
        }

    }

}
