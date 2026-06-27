package lombokdemo;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private String productId;
    private String name;
    private double price;
    private String category;
    private int quantity;
}
