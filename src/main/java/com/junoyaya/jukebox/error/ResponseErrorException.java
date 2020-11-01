package com.junoyaya.jukebox.error;

import org.springframework.web.server.ResponseStatusException;

import lombok.Getter;

@Getter
public class ResponseErrorException extends ResponseStatusException {

  private static final long serialVersionUID = 1522011319610066474L;
  private ErrorCode code;

  public ResponseErrorException(ErrorCode code) {
    super(code.getStatus());
    this.code = code;
  }

  public ResponseErrorException(ErrorCode code, String reason, Throwable cause) {
    super(code.getStatus(), reason, cause);
    this.code = code;
  }

  public ResponseErrorException(ErrorCode code, String reason) {
    super(code.getStatus(), reason);
    this.code = code;
  }
}
