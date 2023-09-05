package f4.service;

import f4.constant.CustomErrorCode;
import f4.dto.EndedAuctionEvent;
import f4.dto.ProductDto;
import f4.dto.UserDto;
import f4.exception.CustomException;
import f4.kafka.EventProducer;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.KafkaException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuctionStatusUpdater {
  private static final String CRON_PROGRESS_TO_END = "0 0 0 * * ?";
  private static final String CRON_WAIT_TO_PROGRESS = "0 0 14 * * ?";
  private final UserServiceAPI userServiceAPI;
  private final ProductServiceAPI productServiceAPI;
  private final EventProducer eventProducer;

  @Scheduled(cron = CRON_PROGRESS_TO_END)
  public void updateAuctionStatusToEnd() {
    try {
      List<ProductDto> updatedProducts = productServiceAPI.auctionStatusUpdateToEnd();
      updatedProducts.forEach(this::updateProductStatusToEnd);
    } catch (FeignException e) {
      log.error("Feign통신에 실패했습니다.");
      throw new CustomException(CustomErrorCode.FEIGN_FAILED, e);
    } catch (KafkaException e) {
      log.error("kafka 이벤트 발행에 실패했습니다.");
      throw new CustomException(CustomErrorCode.EMAIL_EVENT_PUB_FAILED, e);
    }
  }

  @Scheduled(cron = CRON_WAIT_TO_PROGRESS)
  public void updateAuctionStatusToProgress() {
    try {
      productServiceAPI.auctionStatusUpdateToProgress();
    } catch (FeignException e) {
      log.error("Feign통신에 실패했습니다.");
      throw new CustomException(CustomErrorCode.FEIGN_FAILED, e);
    }
  }

  private void updateProductStatusToEnd(ProductDto product) {
    UserDto userDto = userServiceAPI.getUserById(product.getBidUserId());
    EndedAuctionEvent event = createEndedAuctionEvent(product, userDto);
    eventProducer.send(event);
    log.info(String.format("[%s] 발행", event.toString()));
  }

  private EndedAuctionEvent createEndedAuctionEvent(ProductDto product, UserDto userDto) {
    return EndedAuctionEvent.builder()
        .userId(userDto.getUserId())
        .userEmail(userDto.getUserEmail())
        .username(userDto.getUserName())
        .productName(product.getName())
        .image(product.getImage())
        .artist(product.getArtist())
        .auctionPrice(product.getAuctionPrice())
        .auctionEndTime(product.getAuctionEndTime())
        .build();
  }
}
