package pl.sda.springboottraining.errors;

import java.time.LocalDateTime;

public class ErrorEntity {

    private LocalDateTime localDateTime;

    private String error;

    public ErrorEntity() {
        localDateTime = LocalDateTime.now();
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
