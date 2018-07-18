package tk.roydgar.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
@Embeddable
class Address {

    @NotNull
    @Column(name = "address", nullable = false, length = 160)
    private String explicitAddress;

    @NotNull
    @Column(nullable = false)
    private Double longitude;

    @Column(nullable = false)
    private Double latitude;

}
