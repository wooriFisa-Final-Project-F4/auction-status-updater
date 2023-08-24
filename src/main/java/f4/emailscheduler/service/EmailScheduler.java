package f4.emailscheduler.service;

import f4.emailscheduler.dto.EmailEvent;
import f4.emailscheduler.dto.UserDto;
import f4.emailscheduler.dto.ProductDto;
import f4.emailscheduler.constant.CustomErrorCode;
import f4.emailscheduler.exception.CustomException;
import f4.emailscheduler.kafka.EmailProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailScheduler {
  private static final String CRON_EXPRESSION = "0 0 7 * * ?";
  private final ProductServiceAPI productServiceAPI;
  private final UserServiceAPI userServiceAPI;
  private final EmailProducer emailProducer;

  @Scheduled(cron = CRON_EXPRESSION)
  public void sendEmailsToWinners() {
    try {
      List<ProductDto> endedProducts = productServiceAPI.findFinishedAuctionProducts();
      for (ProductDto productDto : endedProducts) {
        sendEmailToWinner(productDto);
      }
    } catch (Exception e) {
      throw new CustomException(CustomErrorCode.EMAIL_EVENT_PUB_FAILED, e);
    }
  }

  private void sendEmailToWinner(ProductDto productDto) {
    UserDto userDto = userServiceAPI.getUserById(productDto.getBidUserId());
    EmailEvent event = buildEmailEvent(productDto, userDto);
    emailProducer.send(event);
  }

  private EmailEvent buildEmailEvent(ProductDto productDto, UserDto userDto) {
    return EmailEvent.builder()
        .userEmail(userDto.getUserEmail())
        .username(userDto.getUserName())
        .productName(productDto.getName())
        .image(extractFirstImage(productDto.getImages()))
        .artist(productDto.getArtist())
        .auctionPrice(productDto.getAuctionPrice())
        .auctionEndTime(String.valueOf(productDto.getAuctionEndTime()))
        .build();
  }

  private String extractFirstImage(String images) {
    return images.split(" ")[0];
  }
}
