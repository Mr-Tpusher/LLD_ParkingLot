package v0;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import v0.constants.VehicleSize;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class VehicleDetails {
    private String registrationNumber;
    private VehicleSize vehicleSize;
}
