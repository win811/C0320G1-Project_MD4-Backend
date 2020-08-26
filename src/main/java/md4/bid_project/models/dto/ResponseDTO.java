package md4.bid_project.models.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseDTO<T> {
    private String status;

    @Builder.Default
    private String message = "Success!";

    private T body;
}
