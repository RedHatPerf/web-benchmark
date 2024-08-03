package org.jboss.perf;

import org.jboss.perf.model.PojoPerson;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

/**
 * @author Radim Vansa &ltrvansa@redhat.com&gt;
 */
@Path("/pojo")
public class PojoHandler {
   private volatile Object blackhole;

   @GET
   @Produces(MediaType.APPLICATION_JSON)
   public PojoPerson getPersonJson() {
      return PojoPerson.JOHNNY;
   }


   @GET
   @Path("/all")
   @Produces(MediaType.APPLICATION_JSON)
   public PojoPerson[] getAll() {
      return new PojoPerson[] { PojoPerson.DANNY, PojoPerson.JOHNNY, PojoPerson.PENNY };
   }

   @POST
   @Consumes(MediaType.APPLICATION_JSON)
   public void postPersonJson(PojoPerson person) {
      blackhole = person;
   }

   @PUT
   @Consumes(MediaType.APPLICATION_JSON)
   @Produces(MediaType.APPLICATION_JSON)
   public PojoPerson putPersonJson(PojoPerson person) {
      blackhole = person;
      return PojoPerson.JOHNNY;
   }
}
