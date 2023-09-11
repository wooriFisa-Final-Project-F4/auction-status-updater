package f4.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class FeignProductDto {
  private Long id;
  private String name;
  private String image;
  private String artist;
  private String auctionPrice;
  private String auctionStatus;
  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
  private LocalDateTime auctionEndTime;
  private long bidUserId;
}
