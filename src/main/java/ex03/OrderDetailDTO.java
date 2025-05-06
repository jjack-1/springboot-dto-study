package ex03;

import lombok.Data;
import model.OrderOption;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class OrderDetailDTO {
    private int orderId;
    private List<ProductDTO> products;
    private int sumPrice;

    public OrderDetailDTO(List<OrderOption> orderOptions) {
        this.orderId = orderOptions.get(0).getId();

        this.sumPrice = orderOptions.stream()
                .mapToInt((orderOption) -> orderOption.getTotalPrice())
                .sum();

        this.products = orderOptions.stream()
                .collect(
                        Collectors.groupingBy(
                                (orderOption) -> orderOption.getProduct().getId()))
                .entrySet()
                .stream()
                .map((entry) -> new ProductDTO(entry.getKey(), entry.getValue()))
                .toList();
    }

    @Data
    public static class ProductDTO {
        private int productId;
        private List<OrderOptionDTO> orderOptions;

        public ProductDTO(int productId, List<OrderOption> orderOptions) {
            this.productId = productId;

            this.orderOptions = orderOptions.stream()
                    .map(orderOption -> new OrderOptionDTO(orderOption))
                    .toList();
        }

        @Data
        public static class OrderOptionDTO {
            private int orderOptionId;
            private String orderOptionName;
            private int orderOptionQty;
            private int orderOptionTotalPrice;

            public OrderOptionDTO(OrderOption orderOption) {
                this.orderOptionId = orderOption.getId();
                this.orderOptionName = orderOption.getOptionName();
                this.orderOptionQty = orderOption.getQty();
                this.orderOptionTotalPrice = orderOption.getTotalPrice();
            }
        }
    }
}