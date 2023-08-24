package f4.emailscheduler.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ProductDto {
  private String name;
  private String images;
  private String artist;
  private String bidPrice;
  private LocalDateTime auctionEndTime;
  private String bidUserId;
}
