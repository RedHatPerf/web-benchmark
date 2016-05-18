package org.jboss.perf

import javax.ws.rs.core.MediaType

import io.gatling.core.Predef._

/**
  * @author John O'Hara &ltjohara@redhat.com&gt;
  */
trait AppHtml extends BaseSimulation {
  override def protocolConf() = {
    super.protocolConf().acceptHeader(MediaType.TEXT_HTML).contentTypeHeader(MediaType.TEXT_HTML)
  }
}
