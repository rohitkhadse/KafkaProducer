package com.rohitkhadse.kafkaproducer.kafkaproducer.resource;

import com.rohitkhadse.kafkaproducer.kafkaproducer.Util.KafkaProducerConstant;
import com.rohitkhadse.kafkaproducer.kafkaproducer.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("kafka")
public class UserResource {

    @Autowired
    private KafkaTemplate<String, User> kafkaTemplate;
    private static final String TOPIC = KafkaProducerConstant.TOPIC_NAME;

    @GetMapping("/publish/{name}")
    public String publish(@PathVariable("name") String name){

        kafkaTemplate.send(TOPIC,new User(name,KafkaProducerConstant.DEPT,KafkaProducerConstant.SALARY));

        return KafkaProducerConstant.PUBLISH_SUCCESSFULLY_MESSAGE;
    }
}
