package f4.emailscheduler.service;

import f4.emailscheduler.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@FeignClient(name = "PRODUCT-SERVICE")
public interface ProductServiceAPI {

  @GetMapping("")
  List<ProductDto> getProductsToBeEnded();

  @PutMapping("")
  ProductDto auctionStatusUpdate(long productId, String status);
}
