package ex01;

import lombok.Data;
import model.Product;
import model.ProductOption;

import java.util.List;

@Data
public class ProductDetailDTO {
    private int productId;
    private String productName;
    private List<ProductOptionDTO> productOptionDTOs;

    public ProductDetailDTO(
            int productId,
            String productName,
            List<ProductOptionDTO> productOptionDTOs
    ) {
        this.productId = productId;
        this.productName = productName;
        this.productOptionDTOs = productOptionDTOs;
    }

    @Data
    public static class ProductOptionDTO {
        private int optionId;
        private String optionName;
        private int price;
        private int qty;

        public ProductOptionDTO(
                int optionId,
                String optionName,
                int price,
                int qty
        ) {
            this.optionId = optionId;
            this.optionName = optionName;
            this.price = price;
            this.qty = qty;
        }
    }
}
