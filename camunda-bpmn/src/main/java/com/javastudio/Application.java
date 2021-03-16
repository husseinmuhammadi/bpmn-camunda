package com.javastudio;

import com.javastudio.bpmn.ChargeCardWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.net.URI;

public class Application {
    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        ChargeCardWorker chargeCardWorker = new ChargeCardWorker("http://localhost:8480/engine-rest");
        chargeCardWorker.subscribe("charge-card", (externalTask, externalTaskService) -> {
            // Put your business logic here

            // Get a process variable
            String item = (String) externalTask.getVariable("item");
            Long amount = (Long) externalTask.getVariable("amount");

            LOGGER.info("Charging credit card with an amount of '" + amount + "'€ for the item '" + item + "'...");

            try {
                Desktop.getDesktop().browse(new URI("https://docs.camunda.org/get-started/quick-start/complete"));
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Complete the task
            externalTaskService.complete(externalTask);
        });
    }
}
