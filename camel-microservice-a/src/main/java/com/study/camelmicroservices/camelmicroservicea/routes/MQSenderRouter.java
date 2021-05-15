package com.study.camelmicroservices.camelmicroservicea.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MQSenderRouter  extends RouteBuilder {

    //sends message to mq
    @Override
    public void configure() throws Exception {
        //timer to trigger it
        from("timer:active-mq-timer?period=10000")
                .transform().constant("MQ Sender Message")
                .log("${body}")
                .to("activemq:my-activemq-queue");
        //to mq, creates queue if not exist.
    }
}
