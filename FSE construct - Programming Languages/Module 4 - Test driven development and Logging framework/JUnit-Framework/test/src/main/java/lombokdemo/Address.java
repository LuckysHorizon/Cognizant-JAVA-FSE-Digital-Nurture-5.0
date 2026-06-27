package lombokdemo;

import lombok.Builder;
import lombok.ToString;
import lombok.Getter;

@Getter
@Builder
@ToString
public class Address {
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String country;
}
