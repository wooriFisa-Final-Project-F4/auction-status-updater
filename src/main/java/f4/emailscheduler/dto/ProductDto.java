package f4.emailscheduler.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ProductDto {
  private long id;
  private String name;
  private String image;
  private String artist;
  private String auctionPrice;
  private String auctionStatus;
  private LocalDateTime auctionEndTime;
  private long bidUserId;
}
