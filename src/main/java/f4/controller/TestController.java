package f4.controller;

import f4.dto.EndedAuctionEvent;
import f4.kafka.EventProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/scheduler")
public class TestController {
    private final EventProducer eventProducer;

    @PostMapping("/")
    public ResponseEntity<String> send(){
        EndedAuctionEvent event = EndedAuctionEvent.builder()
         .userId(1L)
         .userEmail("kfromh0136@gmail.com")
         .username("김지운")
         .productName("Life and...Energy21103")
         .image( "https://www.singulart.com/images-sh/eyJidWNrZXQiOiJzaW5ndWxhcnQtd2Vic2l0ZS1wcm9kIiwia2V5IjoiYXJ0d29ya3NcL3YyXC9jcm9wcGVkXC8xNzYyMVwvbWFpblwvem9vbVwvMTMyNjMxNV84MWMyNmZkMjQ5YzU5MjBmNzkxYjAwYmUwMTcyMmEyYS5qcGVnIiwiZWRpdHMiOnsicmVzaXplIjp7IndpZHRoIjoxNTAwLCJoZWlnaHQiOjE1MDAsImZpdCI6Imluc2lkZSIsImJhY2tncm91bmQiOm51bGx9LCJ0b0Zvcm1hdCI6IndlYnAifX0=?signature=a9d5b3b20fae6300cb893616a8d643f789d7a2fa585e58d2b8f42c3197a6180f")
         .artist("Eunha Jung")
         .auctionPrice("2232000")
         .auctionEndTime(LocalDateTime.now())
         .build();
        eventProducer.send(event);
        return ResponseEntity.ok("센드 성공");
    }

}
