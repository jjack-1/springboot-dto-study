package ex03;

import lombok.Data;
import model.OrderOption;

// 3번 문제용
@Data
public class TempDTO {
    private int orderId;
    private int productId;
    private int orderOptionId;
    private String orderOptionName;
    private int orderOptionQty;
    private int totalPrice;

    public TempDTO(OrderOption orderOption) {
        this.orderId = orderOption.getOrder().getId();
        this.productId = orderOption.getProduct().getId(); // lazy loading (조인 안하면)
        this.orderOptionId = orderOption.getId();
        this.orderOptionName = orderOption.getOptionName();
        this.orderOptionQty = orderOption.getQty();
        this.totalPrice = orderOption.getTotalPrice();
    }
}
