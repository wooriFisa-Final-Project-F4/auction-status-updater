package f4.emailscheduler.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class EmailEvent {
  private String userEmail;
  private String username;
  private String productName;
  private String image;
  private String artist;
  private String auctionPrice;
  private String auctionEndTime;
}
