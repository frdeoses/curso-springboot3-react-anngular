package academy.atl.customer.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Customer {

    private Integer id;

    private String firstname;

    private String lastname;

    private String email;

    private String address;

}
