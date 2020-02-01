# Bank Balance Exactly Once
This is an implementation of exercise: Bank Balance in 
Udemy course: _Apache Kafka Series - Kafka Streams for Data Processing_.

Tech Stack:
- Spring Boot
- Kafka Stream
- Kafka
- Gradle

This exercise is focusing on the [Exactly-once semantics of Kafka](https://medium.com/@jaykreps/exactly-once-support-in-apache-kafka-55e1fdd0a35f).

> __WARNING__: this might not be the best practice.


## Run
You need to have a kafka bootstrap-server running at `localhost:9092`.
```shell script
gradle bootRun
```

