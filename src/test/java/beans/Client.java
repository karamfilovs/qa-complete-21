package beans;

import lombok.Builder;
import lombok.Data;


@Builder
@Data
/**
 * POJO class for mandatory fields for creation of a client
 */
public class Client {
    private String firm_name;
    private String firm_town;
    private String firm_addr;
    private Boolean firm_is_reg_vat;
    private String firm_mol;
    private String firm_vat_number;
}
