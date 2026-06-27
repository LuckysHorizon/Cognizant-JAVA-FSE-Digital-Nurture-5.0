package lombokdemo;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class Order {
    private String orderId;
    private String customerName;
    private double totalAmount;

    @Builder.Default
    private String status = "PENDING";

    @Builder.Default
    private LocalDateTime orderDate = LocalDateTime.now();

    @Builder.Default
    private List<String> items = new ArrayList<>();
}
