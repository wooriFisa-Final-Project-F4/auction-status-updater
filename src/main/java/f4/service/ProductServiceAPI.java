package f4.service;

import f4.dto.FeignProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@FeignClient(name = "PRODUCT-SERVICE")
public interface ProductServiceAPI {
  @PutMapping("/api/product/v1/update-to-end")
  List<FeignProductDto> auctionStatusUpdateToEnd();

  @PutMapping("/api/product/v1/update-to-progress")
  void auctionStatusUpdateToProgress();
}
