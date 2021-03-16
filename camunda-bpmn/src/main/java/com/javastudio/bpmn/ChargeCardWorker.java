package com.javastudio.bpmn;

import org.camunda.bpm.client.ExternalTaskClient;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.net.URI;

public class ChargeCardWorker {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChargeCardWorker.class);

    private final String baseUrl;

    public ChargeCardWorker(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public void subscribe(String topicName, ExternalTaskHandler taskHandler) {
        ExternalTaskClient client = ExternalTaskClient.create()
                .baseUrl(baseUrl)
                .asyncResponseTimeout(10000) // long polling timeout
                .build();

        // subscribe to an external task topic as specified in the process
        client.subscribe(topicName)
                .lockDuration(1000) // the default lock duration is 20 seconds, but you can override this
                .handler(taskHandler)
                .open();
    }
}
