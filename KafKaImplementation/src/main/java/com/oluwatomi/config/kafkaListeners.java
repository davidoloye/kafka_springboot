package com.oluwatomi.config;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class kafkaListeners {

    @KafkaListener(
            topics = "oluwatomi",
            groupId = "groupId"
    )
    void listener(String data){
        System.out.println("Listener Received ::: " + data +  " 🤪");
    }
}
