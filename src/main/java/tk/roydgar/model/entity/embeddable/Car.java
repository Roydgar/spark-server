package tk.roydgar.model.entity.embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class Car {

    @Column(name = "car_brand", length = 60)
    private String carBrand;

    @Column(name = "car_model" , length = 60)
    private String carModel;

}
