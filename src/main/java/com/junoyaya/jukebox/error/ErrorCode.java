package com.junoyaya.jukebox.error;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum ErrorCode {
  /**
   * General bad http request.
   */
  BAD_REQUEST(HttpStatus.BAD_REQUEST, "Bad Request"),

  /**
   * General bad http request.
   */
  INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Bad Request"),

  /**
   * General object not exist error resulted from a bad http request.
   */
  NOT_EXIST(HttpStatus.NOT_FOUND, "Bad Request: Object does not exist.");

  private final HttpStatus status;

  private final String message;

  private ErrorCode(HttpStatus status, String message) {
    this.status = status;
    this.message = message;
  }
}
