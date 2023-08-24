package f4.emailscheduler.service;

import f4.emailscheduler.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "USER-SERVICE")
public interface UserServiceAPI {
  @GetMapping("api/v1/{userId}")
  UserDto getUserById(@PathVariable("userId") String userId);
}
