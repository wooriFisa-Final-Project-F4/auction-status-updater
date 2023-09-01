package f4.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CustomErrorCode {
    EMAIL_EVENT_PUB_FAILED(401, "이벤트 발행에 실패했습니다."),
    FEIGN_FAILED(400,"API 요청에 실패했습니다.");
    private final int code;
    private final String message;
}
