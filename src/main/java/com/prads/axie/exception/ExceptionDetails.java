package com.prads.axie.exception;

public class ExceptionDetails {
  private String title;

  private Integer status;

  private String detail;

  private Long timestamp;

  private String message;

  public ExceptionDetails() {
    super();
  }

  private ExceptionDetails(builder b) {
    this.title = b.title;
    this.status = b.status;
    this.detail = b.detail;
    this.timestamp = b.timestamp;
    this.message = b.message;
  }

  public static class builder {
    private String title;

    private Integer status;

    private String detail;

    private Long timestamp;

    private String message;

    public builder title(String title) {
      this.title = title;
      return this;
    }

    public builder status(Integer status) {
      this.status = status;
      return this;
    }

    public builder detail(String detail) {
      this.detail = detail;
      return this;
    }

    public builder timestamp(Long timestamp) {
      this.timestamp = timestamp;
      return this;
    }

    public builder message(String message) {
      this.message = message;
      return this;
    }

    public ExceptionDetails build() {
      return new ExceptionDetails(this);
    }
  }



}
