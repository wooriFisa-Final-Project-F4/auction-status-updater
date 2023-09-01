package f4.service;

import f4.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "USER-SERVICE")
public interface UserServiceAPI {
  @GetMapping("/user/v1/email/{userId}")
  UserDto getUserById(@PathVariable("userId") long userId);
}
