package jp.co.icg.base.response;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ComResponse {

    public static final String SUCCESS = "success";
    public static final String FAILURE = "failure";

    private String result;
    private String message;
    private Object responseObject;
    private List<String> errors;

    public static ResponseEntity<ComResponse> validatedException(List<String> response) {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(ComResponse.builder()
            .result(ComResponse.FAILURE)
            .errors(response)
            .build());
    }

    public static ResponseEntity<ComResponse> getFailureResponse(List<String> response) {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(ComResponse.builder()
            .result(ComResponse.FAILURE)
            .errors(response)
            .build());
    }
}
