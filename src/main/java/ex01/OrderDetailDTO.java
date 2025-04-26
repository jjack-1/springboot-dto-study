package ex01;

import lombok.Data;

import java.util.List;

@Data
public class OrderDetailDTO {
    private int orderId;
    private int sumPrice;
    private List<ProductDTO> productDTOs;

    public OrderDetailDTO(
            int orderId,
            int sumPrice,
            List<ProductDTO> productDTOs
    ) {
        this.orderId = orderId;
        this.sumPrice = sumPrice;
        this.productDTOs = productDTOs;
    }

    @Data
    public static class ProductDTO {
        private int productId;
        private List<ProductOptionDTO> ProductOptionDTOs;

        public ProductDTO(
                int productId,
                List<ProductOptionDTO> ProductOptionDTOs
        ) {
            this.productId = productId;
            this.ProductOptionDTOs = ProductOptionDTOs;
        }

        @Data
        public static class ProductOptionDTO {
            private int orderOptionId;
            private String orderOptionName;
            private int orderQty;
            private int OrderTotalPrice;

            public ProductOptionDTO(
                    int orderOptionId,
                    String orderOptionName,
                    int orderQty,
                    int OrderTotalPrice
            ) {
                this.orderOptionId = orderOptionId;
                this.orderOptionName = orderOptionName;
                this.orderQty = orderQty;
                this.OrderTotalPrice = OrderTotalPrice;
            }
        }
    }
}
