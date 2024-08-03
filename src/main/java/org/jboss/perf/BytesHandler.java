package org.jboss.perf;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.Random;

/**
 * @author Radim Vansa &ltrvansa@redhat.com&gt;
 */
@Path("/bytes")
public class BytesHandler {
   public final static byte[] SOME_BYTES = new byte[512];
   private volatile Object blackhole;

   static {
      new Random().nextBytes(SOME_BYTES);
   }

   @GET
   @Produces(MediaType.APPLICATION_OCTET_STREAM)
   public byte[] getBytes() {
      return SOME_BYTES;
   }

   @POST
   @Consumes(MediaType.APPLICATION_OCTET_STREAM)
   public void postText(byte[] bytes) {
      blackhole = bytes;
   }

   @PUT
   @Consumes(MediaType.APPLICATION_OCTET_STREAM)
   @Produces(MediaType.APPLICATION_OCTET_STREAM)
   public byte[] putText(byte[] bytes) {
      blackhole = bytes;
      return bytes;
   }
}
