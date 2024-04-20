package gns.diecastfinder.wanted.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class WantedModelDto {
    private UUID id;
    private String name;
    private String producer;
    private Integer minPrice;
    private Integer maxPrice;
    private String currency;
}
