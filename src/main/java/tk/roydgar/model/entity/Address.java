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
public class Address {

    @NotNull
    @Column(nullable = false, length = 60)
    private String city;

    @NotNull
    @Column(nullable = false, length = 60)
    private String street;

    @NotNull
    @Column(nullable = false, length = 30)
    private String house;

    @NotNull
    @Column(nullable = false)
    private Double longitude;

    @Column(nullable = false)
    private Double latitude;

}
