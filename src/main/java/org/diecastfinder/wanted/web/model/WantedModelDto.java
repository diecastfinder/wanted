package org.diecastfinder.wanted.web.model;

import lombok.*;
import lombok.experimental.Accessors;

import java.util.Objects;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
@ToString
public class WantedModelDto {

    private UUID id;

    private String name;
    private String producer;
    private Integer minPrice;
    private Integer maxPrice;
    private String currency;
    private boolean active;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WantedModelDto that = (WantedModelDto) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
