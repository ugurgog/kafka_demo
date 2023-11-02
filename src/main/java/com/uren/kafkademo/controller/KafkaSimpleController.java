package com.uren.kafkademo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uren.kafkademo.model.Student;
import com.uren.kafkademo.model.Lesson;
import com.uren.kafkademo.service.KafkaSimpleService;

@RestController
@RequestMapping("api/kafka")
public class KafkaSimpleController {
  
  private KafkaSimpleService kafkaSimpleService;
  
  @Autowired
  public KafkaSimpleController(KafkaSimpleService kafkaSimpleService) {
    this.kafkaSimpleService = kafkaSimpleService;
  }

  @PostMapping
  public void post(@RequestBody Student model) {
    kafkaSimpleService.sendStudentData(model);
  }
  
  @PostMapping("/v2")
  public void postV2(@RequestBody Lesson model) {
    kafkaSimpleService.sendLessonData(model);
  }
  
  @PostMapping("/send-object")
  public void postObject(@RequestBody Student model) {
    kafkaSimpleService.sendStudentDataObject(model);
  }
}
