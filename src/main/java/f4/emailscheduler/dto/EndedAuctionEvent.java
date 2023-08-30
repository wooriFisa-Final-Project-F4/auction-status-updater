package f4.emailscheduler.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
public class EndedAuctionEvent {
  private long userId;
  private String userEmail;
  private String username;
  private String productName;
  private String image;
  private String artist;
  private String auctionPrice;
  private LocalDateTime auctionEndTime;
}
