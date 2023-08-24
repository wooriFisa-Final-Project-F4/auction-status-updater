package f4.emailscheduler.service;

import f4.emailscheduler.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "PRODUCT-SERVICE")
public interface ProductServiceAPI {
    @GetMapping("api/v1/finished-auction-products")
    List<ProductDto> findFinishedAuctionProducts();
}
