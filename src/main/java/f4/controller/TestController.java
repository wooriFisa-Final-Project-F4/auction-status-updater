package f4.controller;

import f4.controller.dto.TestDto;
import f4.dto.EndedAuctionEvent;
import f4.kafka.EventProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/scheduler")
public class TestController {
    private final EventProducer eventProducer;

    @PostMapping("/")
    public ResponseEntity<String> send(
        @RequestBody TestDto testDto
    ){
        EndedAuctionEvent event = EndedAuctionEvent.builder()
         .userId(testDto.getUserId())
         .userEmail(testDto.getEmail())
         .username(testDto.getUserName())
         .productName(testDto.getProductName())
         .image(testDto.getImage())
         .artist(testDto.getArtist())
         .auctionPrice(testDto.getAuctionPrice())
         .auctionEndTime(LocalDateTime.now())
         .build();
        eventProducer.send(event);
        return ResponseEntity.ok("센드 성공");
    }

}
