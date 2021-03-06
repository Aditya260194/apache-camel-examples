package com.study.camelmicroservices.camelmicroserviceb.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MQReadRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("activemq:my-activemq-queue")
                .log("${body}")
                .to("log:received-message-from-activemq");
    }
}
