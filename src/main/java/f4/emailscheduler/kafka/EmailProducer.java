package f4.emailscheduler.kafka;

import f4.emailscheduler.dto.EmailEvent;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailProducer {
  private static final Logger LOGGER = LoggerFactory.getLogger(EmailProducer.class);
  private NewTopic topic;
  private KafkaTemplate<String, EmailEvent> kafkaTemplate;

  public void send(EmailEvent event) {
    LOGGER.info(String.format("Email event => %s", event.toString()));

    Message<EmailEvent> message =
        MessageBuilder.withPayload(event).setHeader(KafkaHeaders.TOPIC, topic.name()).build();
    kafkaTemplate.send(message);
  }
}
