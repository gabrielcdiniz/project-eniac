package br.com.jorgerabellodev.eniacproject.api.errors.details;

import java.io.Serializable;

public class ErrorDetails implements Serializable {

    private Integer status;
    private String errorMessage;
    private Long timeStamp;

    public ErrorDetails(Integer status, String errorMessage, Long timeStamp) {
        this.status = status;
        this.errorMessage = errorMessage;
        this.timeStamp = timeStamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
