package org.jboss.perf;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * @author Radim Vansa &ltrvansa@redhat.com&gt;
 */
@Path("/text")
public class TextHandler {
   private volatile Object blackhole;

   @GET
   @Produces(MediaType.TEXT_PLAIN)
   public String getText() {
      return "foo";
   }

   @POST
   @Consumes(MediaType.TEXT_PLAIN)
   public void postText(String text) {
      blackhole = text;
   }

   @PUT
   @Consumes(MediaType.TEXT_PLAIN)
   @Produces(MediaType.TEXT_PLAIN)
   public String putText(String text) {
      blackhole = text;
      return "goo";
   }
}
