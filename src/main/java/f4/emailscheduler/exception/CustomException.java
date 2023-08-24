package f4.emailscheduler.exception;

import f4.emailscheduler.constant.CustomErrorCode;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
  private final CustomErrorCode customErrorCode;

  public CustomException(CustomErrorCode customErrorCode, Throwable cause) {
    super(customErrorCode.getMessage(), cause);
    this.customErrorCode = customErrorCode;
  }
}
