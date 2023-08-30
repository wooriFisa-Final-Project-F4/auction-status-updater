package f4.emailscheduler.kafka;

import f4.emailscheduler.dto.EndedAuctionEvent;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class EventProducer {
  private static final Logger LOGGER = LoggerFactory.getLogger(EventProducer.class);

  private NewTopic newTopic;
  private KafkaTemplate<String, EndedAuctionEvent> kafkaTemplate;

  public void send(EndedAuctionEvent event) {
    Message<EndedAuctionEvent> message =
        MessageBuilder.withPayload(event).setHeader(KafkaHeaders.TOPIC, newTopic.name()).build();
    kafkaTemplate.send(message);
    LOGGER.info(String.format("[%s] event Send=> %s", LocalDateTime.now(), event));
  }
}
