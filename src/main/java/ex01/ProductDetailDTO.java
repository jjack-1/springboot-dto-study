package ex01;

import lombok.Data;
import model.Product;
import model.ProductOption;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProductDetailDTO {
    private int productId;
    private String productName;
    private List<ProductOptionDTO> productOptions = new ArrayList<>(); // 이름에는 DTO를 넣지 않는다

    public ProductDetailDTO(Product product, List<ProductOption> options) {
        this.productId = product.getId();
        this.productName = product.getName();
        for (ProductOption option : options) {
            this.productOptions.add(new ProductOptionDTO(option));
        }
    }

    @Data
    public static class ProductOptionDTO {
        private int optionId;
        private String optionName;
        private int optionPrice;
        private int optionQty;

        public ProductOptionDTO(ProductOption productOption) {
            this.optionId = productOption.getId();
            this.optionName = productOption.getName();
            this.optionPrice = productOption.getPrice();
            this.optionQty = productOption.getQty();
        }
    }
}
