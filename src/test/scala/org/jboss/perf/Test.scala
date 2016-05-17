package org.jboss.perf

import javax.ws.rs.core.MediaType

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._
import io.gatling.http.request.builder.Http

import scala.concurrent.duration._;

/**
  * @author Radim Vansa &ltrvansa@redhat.com&gt;
  */
class Test extends BaseSimulation {

  override def root() = "/basicauth/service"

  override def protocolConf() = super.protocolConf().acceptHeader(MediaType.TEXT_HTML).disableFollowRedirect

  override def run(http: Http) = null

  override def run(scenarioBuilder: ScenarioBuilder): ScenarioBuilder = {
    // cookies are handled transparently by gatling
    scenarioBuilder
      // ignore
      .exec(http("First").get("/echo").queryParam("value", "foo"))
//          .check(status.is(302)).check(header("Location").saveAs("auth-location")))
//      .exec(http("Auth").get("${auth-location}")
//        .check(regex("action=\"([^\"]*)\"").ofType[String].saveAs("login-location")))
//        .pause(10 seconds)
//      .exec(http("Login").post("${login-location}").formParam("username", "radim").formParam("password", "radim").formParam("login", "Log in")
//        .check(status.is(302)).check(header("Location").saveAs("redirect-location")))
//      .exec(http("Redirect").get("${redirect-location}")
//        .check(status.is(302)).check(header("Location").saveAs("original-location")))
//      .exec(http("Original").get("${original-location}")
//        .check(bodyString.is("foo")))
  }
}
