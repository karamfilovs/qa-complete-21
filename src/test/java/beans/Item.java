package beans;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Item {
    private String name;
    private int price_for_quantity;
    private String quantity_unit;
    private int warranty;
}
