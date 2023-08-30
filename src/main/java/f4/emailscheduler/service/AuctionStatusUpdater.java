package f4.emailscheduler.service;

import f4.emailscheduler.constant.CustomErrorCode;
import f4.emailscheduler.dto.EndedAuctionEvent;
import f4.emailscheduler.dto.ProductDto;
import f4.emailscheduler.dto.UserDto;
import f4.emailscheduler.exception.CustomException;
import f4.emailscheduler.kafka.EventProducer;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.KafkaException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuctionStatusUpdater {
  private static final String CRON_EXPRESSION = "0 0 0 * * ?";
  private final UserServiceAPI userServiceAPI;
  private final ProductServiceAPI productServiceAPI;
  private final EventProducer eventProducer;

  @Scheduled(cron = CRON_EXPRESSION)
  public void updateAuctionStatus() {
    try {
      List<ProductDto> productsToBeEnded = productServiceAPI.getProductsToBeEnded();
      productsToBeEnded.forEach(this::updateProductStatus);
    } catch (FeignException e) {
      throw new CustomException(CustomErrorCode.FEIGN_FAILED, e);
    } catch (KafkaException e) {
      throw new CustomException(CustomErrorCode.EMAIL_EVENT_PUB_FAILED, e);
    }
  }

  private void updateProductStatus(ProductDto product) {
    ProductDto updatedProduct = productServiceAPI.auctionStatusUpdate(product.getId(), "END");
    UserDto userDto = userServiceAPI.getUserById(updatedProduct.getBidUserId());
    EndedAuctionEvent event = createEndedAuctionEvent(updatedProduct, userDto);
    eventProducer.send(event);
  }

  private EndedAuctionEvent createEndedAuctionEvent(ProductDto updatedProduct, UserDto userDto) {
    return EndedAuctionEvent.builder()
        .userId(userDto.getUserId())
        .userEmail(userDto.getUserEmail())
        .username(userDto.getUserName())
        .productName(updatedProduct.getName())
        .image(updatedProduct.getImage())
        .artist(updatedProduct.getArtist())
        .auctionPrice(updatedProduct.getAuctionPrice())
        .auctionEndTime(updatedProduct.getAuctionEndTime())
        .build();
  }
}
