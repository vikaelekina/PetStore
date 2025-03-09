package tests;

import lombok.Getter;
import model.ApiResponse;

@Getter
public enum Errors {
    PETNOTFOUND(new ApiResponse(1, "error", "Pet not found"));

    private final ApiResponse responseModel;

    Errors(ApiResponse responseModel) {
        this.responseModel = responseModel;
    }
}
