package com.uren.kafkademo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.uren.kafkademo.model.Student;
import com.uren.kafkademo.config.PropertiesConfig;
import com.uren.kafkademo.model.Lesson;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KafkaSimpleService {
  
  private KafkaTemplate<String, String> kafkaTemplate;
  private KafkaTemplate<String, Student> kafkaTemplateStudent;
  private Gson gson;
  
  @Autowired
  public KafkaSimpleService(KafkaTemplate<String, String> kafkaTemplate, Gson gson,
      KafkaTemplate<String, Student> kafkaTemplateStudent) {
    this.kafkaTemplate = kafkaTemplate;
    this.kafkaTemplateStudent = kafkaTemplateStudent;
    this.gson = gson;
  }

  public void sendStudentData(Student model) {
    kafkaTemplate.send(PropertiesConfig.getProperty("topics.student"), gson.toJson(model));
  }
  
  public void sendLessonData(Lesson model) {
    kafkaTemplate.send(PropertiesConfig.getProperty("topics.lesson"), gson.toJson(model));
  }
  
  @KafkaListener(topics = "student", groupId = "myGroupId", containerFactory = "kafkaListenerContainerFactory")
  public void getStudentData(String model) {
    Student simpleModel = gson.fromJson(model, Student.class);
    log.info("::getStudentData model:{}", gson.toJson(simpleModel));
  }
  
  @KafkaListener(topics = "lesson", groupId = "myGroupId", containerFactory = "kafkaListenerContainerFactory")
  public void getLessonData(String model) {
    Lesson simpleModel = gson.fromJson(model, Lesson.class);
    log.info("::getLessonData model:{}", gson.toJson(simpleModel));
  }
  
  //Object send and get ---------------
  public void sendStudentDataObject(Student model) {
    kafkaTemplateStudent.send("studentObjectTopic", model);
  }
  
  @KafkaListener(topics = "studentObjectTopic", groupId = "myGroupId", containerFactory = "kafkaListenerContainerFactoryStudent")
  public void getStudentDataObject(Student model) {
    log.info("::getStudentDataObject model:{}", gson.toJson(model));
  }
}
