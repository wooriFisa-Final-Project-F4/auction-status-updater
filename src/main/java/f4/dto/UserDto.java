package f4.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDto {
  private long userId;
  private String userEmail;
  private String userName;
}
