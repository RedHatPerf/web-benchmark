package org.jboss.perf;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

/**
 * @author Radim Vansa &ltrvansa@redhat.com&gt;
 */
@ApplicationPath("/test")
public class Activator extends Application {
   public static final String ROOT_PATH = "/web-benchmark/test";

   @Provider
   public static class QuietNotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {
      @Override
      public Response toResponse(NotFoundException exception) {
         return Response.status(Response.Status.NOT_FOUND).build();
      }
   }
}
