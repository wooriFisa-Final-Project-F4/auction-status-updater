package f4.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TestDto {

  private Long userId;
  private String email;
  private String userName;
  private String productName;
  private String image;
  private String artist;
  private String auctionPrice;
}
