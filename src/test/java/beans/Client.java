package beans;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Client {
    private String firm_name;
    private String firm_town;
    private String firm_addr;
    private boolean firm_is_reg_vat;
    private String firm_mol;
    private String firm_vat_number;
    private boolean is_person;
    private String person_name;

}
