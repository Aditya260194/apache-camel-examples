package com.study.camelmicroservices.camelmicroservicea.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

//@Component
public class FirstRouter extends RouteBuilder {

    @Autowired
    CurrentTimeBean currentTimeBean;

    @Autowired
    SimpleLogger simpleLogger;

    @Override
    public void configure() throws Exception {
        from("timer:first-timer")
                //.transform().constant("My message") // transformation using transform method

                //.bean("currentTimeBean") //using bean but not autowiring the bean, passing object in string in camel case

                .bean(currentTimeBean) //transformation via bean

                .bean(simpleLogger) //processing the message body

                .process(new SimpleProcessor())// processing using processor
                .to("log:first-timer");
    }
}

@Component
class CurrentTimeBean {
    public String getCurrentTime(){
        return "Time is : "+ LocalDateTime.now();
    }
}

@Component
class SimpleLogger {
    Logger logger = LoggerFactory.getLogger(SimpleLogger.class);
    public void process( String message){
        logger.info("Processing not transforming {} ",message);
    }
}

class SimpleProcessor implements Processor {
    Logger logger = LoggerFactory.getLogger(SimpleProcessor.class);

    @Override
    public void process(Exchange exchange) throws Exception {
        logger.info("Processing not transforming using process method {} ",exchange.getMessage().getBody());
    }
}

