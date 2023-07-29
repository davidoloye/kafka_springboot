package com.oluwatomi.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oluwatomi.model.Content;
import com.oluwatomi.service.kService;
import org.apache.el.util.ExceptionUtils;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KListener {


    private final kService kService;

    public KListener(com.oluwatomi.service.kService kService) {
        this.kService = kService;
    }


    @KafkaListener(topics = "quickstart", groupId = "group_id", containerFactory = "myConsumerFactory")
    public void consume(String msg)  {
        System.out.println(msg);


        try {
            ObjectMapper obj = new ObjectMapper();
            Content topicData = obj.readValue(msg, Content.class);
            if (topicData.getAge() < 18) {
                kService.sendDataToTopic("kidsTopic", topicData.getMessage());
            } else {
                kService.sendDataToTopic("adultTopic", topicData.getMessage());
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
