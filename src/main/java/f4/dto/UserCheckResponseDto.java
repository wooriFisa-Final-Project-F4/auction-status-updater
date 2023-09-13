package f4.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserCheckResponseDto {
  private Long userId;
  private String username;
  private String email;
}
