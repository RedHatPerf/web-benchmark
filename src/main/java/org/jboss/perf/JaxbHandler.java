package org.jboss.perf;

import org.jboss.perf.model.JaxbPerson;
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
@Path("/jaxb")
public class JaxbHandler {
   private volatile Object blackhole;

   @GET
   @Produces(MediaType.APPLICATION_JSON)
   public JaxbPerson getPersonJson() {
      return JaxbPerson.JOHNNY;
   }

   @POST
   @Consumes(MediaType.APPLICATION_JSON)
   public void postPersonJson(JaxbPerson person) {
      blackhole = person;
   }

   @PUT
   @Consumes(MediaType.APPLICATION_JSON)
   @Produces(MediaType.APPLICATION_JSON)
   public JaxbPerson putPersonJson(JaxbPerson person) {
      blackhole = person;
      return JaxbPerson.JOHNNY;
   }

   @GET
   @Produces(MediaType.APPLICATION_XML)
   public JaxbPerson getPersonXml() {
      return JaxbPerson.JOHNNY;
   }

   @POST
   @Consumes(MediaType.APPLICATION_XML)
   public void postPersonXml(JaxbPerson person) {
      blackhole = person;
   }

   @PUT
   @Consumes(MediaType.APPLICATION_XML)
   @Produces(MediaType.APPLICATION_XML)
   public JaxbPerson putPersonXml(JaxbPerson person) {
      blackhole = person;
      return JaxbPerson.JOHNNY;
   }
}
