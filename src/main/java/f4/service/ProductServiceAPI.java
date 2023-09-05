package f4.service;

import f4.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@FeignClient(name = "PRODUCT-SERVICE")
public interface ProductServiceAPI {
  @PutMapping("")
  List<ProductDto> auctionStatusUpdateToEnd();

  @PutMapping("")
  void auctionStatusUpdateToProgress();
}
