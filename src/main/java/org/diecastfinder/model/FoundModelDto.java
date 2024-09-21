package org.diecastfinder.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
@ToString
public class FoundModelDto {
    private String uri;
    private String nameRequested;
    private String lotTitle;
    private String producer;
    private Integer price;
    private String currency;

    @SneakyThrows
    public String asJsonString() {
        return new ObjectMapper().writeValueAsString(this);
    }

    public List<FoundModelDto> asResponse() {
        return List.of(this);
    }

    @SneakyThrows
    public String asResponseJsonString() {
        return new ObjectMapper().writeValueAsString(this.asResponse());
    }
}
