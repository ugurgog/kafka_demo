package com.uren.kafkademo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkademoApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkademoApplication.class, args);
	}

	
	//ugur.gogebakan@machine:~$ cd /opt/kafka_2.12-3.6.0
  //ugur.gogebakan@machine:~$ bin/zookeeper-server-start.sh config/zookeeper.properties zookeper start edilir
	
  //ugur.gogebakan@machine:~$ cd /opt/kafka_2.12-3.6.0
  //ugur.gogebakan@machine:~$ bin/kafka-server-start.sh config/server.properties kafka server start edilir
	
	//ugur.gogebakan@machine:/opt/kafka_2.12-3.6.0$ bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic myTopic consumer console calıstırılır
	
}
