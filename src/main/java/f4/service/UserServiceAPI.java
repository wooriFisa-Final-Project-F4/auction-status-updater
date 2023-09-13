package f4.service;

import f4.dto.UserCheckResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "USER-SERVICE")
public interface UserServiceAPI {
  @GetMapping("/api/user/v1/feign/{userId}")
  UserCheckResponseDto getUserById(@PathVariable("userId") Long userId);
}
