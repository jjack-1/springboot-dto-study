package ex01;

import lombok.Data;
import model.OrderOption;

import java.util.List;

// 3번 문제용
@Data
public class TempDTO {
    private int orderId;
    private int productId;
    private List<OrderOptionDTO> OrderOptionDTO;
    private int totalPrice;

    public TempDTO(
            int orderId,
            int productId,
            List<OrderOptionDTO> orderOptionDTO,
            int totalPrice
    ) {
        this.orderId = orderId;
        this.productId = productId;
        OrderOptionDTO = orderOptionDTO;
        this.totalPrice = totalPrice;
    }

    @Data
    public static class OrderOptionDTO {
        private int id;
        private String optionName;
        private int qty;
        private int totalPrice;

        public OrderOptionDTO(
                int id,
                String optionName,
                int qty,
                int totalPrice
        ) {
            this.id = id;
            this.optionName = optionName;
            this.qty = qty;
            this.totalPrice = totalPrice;
        }
    }
}
