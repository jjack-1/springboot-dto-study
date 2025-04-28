package ex01;

import lombok.Data;
import model.OrderOption;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class OrderDetailDTO {
    private int orderId;
    private List<ProductDTO> products = new ArrayList<>();
    private int sumPrice;

    public OrderDetailDTO(List<OrderOption> orderOptions) {
        // List 형태로 받아서 같은 주문이기 때문에 0번지로 찾는다
        this.orderId = orderOptions.get(0).getId();

        // 중복이 제거된 상품 ids + 전체금액 계산
        // 상품별 구분을 위한 코드
        Set<Integer> productIds = new HashSet<>();
        for (OrderOption orderOption : orderOptions) {
            productIds.add(orderOption.getProduct().getId());
            this.sumPrice += orderOption.getTotalPrice();
        }

        // 상품별 주문한 상품옵션 만들기
        for (Integer productId : productIds) {

            // orderOption이 3개가 있는데 이걸 상품 1, 상품 2로 나눈다
            // 이걸 2개 담은 것과 1개 담은 것으로 따로 만들기 위한 리스트
            // 상품 [p1, p1, p2] -> [p1, p1], [p2]
            List<OrderOption> selectedOptions = new ArrayList<>();
            for (OrderOption orderOption : orderOptions) {
                if (productId == orderOption.getProduct().getId()) {
                    selectedOptions.add(orderOption);
                }
            }
            
            // 상품 DTO는 상품 id와 위에 만든 [p1, p1]을 넣어준다
            ProductDTO productDTO = new ProductDTO(productId, selectedOptions);

            // [{p1}, {p2}] 이렇게 담겨 있는 상품별 리스트
            this.products.add(productDTO);
        }
    }

    @Data
    public static class ProductDTO {
        private int productId;
        private List<OrderOptionDTO> orderOptions;

        public ProductDTO(int productId, List<OrderOption> orderOptions) {
            // List 형태로 받아서 같은 상품이기 때문에 0번지로 찾는다
            this.productId = productId;

            // OrderOption -> OrderOptionDTO
            List<OrderOptionDTO> orderOptionsDTOs = new ArrayList<>();
            for (OrderOption orderOption : orderOptions) {
                orderOptionsDTOs.add(new OrderOptionDTO(orderOption));
            }

            this.orderOptions = orderOptionsDTOs;
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
