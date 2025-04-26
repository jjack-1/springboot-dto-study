package ex01;

import com.google.gson.Gson;
import model.Order;
import model.OrderOption;
import model.Product;
import model.ProductOption;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App1 {
    public static void main(String[] args) {
        // 판매자 로직
        // 1. 상품 2개
        Product p1 = new Product(1, "바지");
        Product p2 = new Product(2, "티");
        List<Product> products = Arrays.asList(p1, p2); // 1번 문제 -> List<Product> -> List<ProductDTO> 옮기기

        // 2. 상품 옵션 4개 생성
        ProductOption op1 = new ProductOption(1, "파란바지", 1000, 10, p1);
        ProductOption op2 = new ProductOption(2, "빨간바지", 2000, 10, p1);
        ProductOption op3 = new ProductOption(3, "노랑티", 1000, 10, p2);
        ProductOption op4 = new ProductOption(4, "하얀티", 2000, 10, p2);
        List<ProductOption> p1Options = Arrays.asList(op1, op2);
        List<ProductOption> p2Options = Arrays.asList(op3, op4); // 2번 문제 -> p2, p2Options -> ProductDetailDTO 로 옮기기

        // 구매자 로직
        // 3. 구매
        Order or1 = new Order(1);
        OrderOption orOption1 = new OrderOption(1, "파란바지", 2, 2000, p1, or1);
        OrderOption orOption2 = new OrderOption(2, "빨간바지", 2, 4000, p1, or1);
        OrderOption orOption3 = new OrderOption(3, "하얀티", 5, 10000, p2, or1);

        op1.setQty(op1.getQty() - 2);
        op2.setQty(op2.getQty() - 2);
        op4.setQty(op4.getQty() - 5);

        Order or2 = new Order(2);
        OrderOption orOption4 = new OrderOption(4, "노랑티", 7, 7000, p2, or2);

        op3.setQty(op3.getQty() - 7);

        Gson gson = new Gson();
        String json;
        // 1번 문제 : 상품 목록 화면
        // List<Product> -> List<ProductDTO>
        List<ProductDTO> productDTOS = new ArrayList<>();
        for (Product product : products) {
            productDTOS.add(
                    new ProductDTO(
                            product.getId(),
                            product.getName()
                    )
            );
        }
        for (ProductDTO productDTO : productDTOS) {
            System.out.println(productDTO);
        }
        System.out.println();
        json = gson.toJson(productDTOS);
        System.out.println(json);
        System.out.println();


        // 2번 문제 : 상품 상세 화면 (p2)
        // Product(p2, p2Options) -> ProductDetail
        List<ProductDetailDTO.ProductOptionDTO> productOptionDTOS = new ArrayList<>();
        for (ProductOption productOption : p2Options) {
            productOptionDTOS.add(
                    new ProductDetailDTO.ProductOptionDTO(
                            productOption.getId(),
                            productOption.getName(),
                            productOption.getPrice(),
                            productOption.getQty()
                    )
            );
        }
        ProductDetailDTO productDetailDTO = new ProductDetailDTO(
                p2.getId(),
                p2.getName(),
                productOptionDTOS
        );
        System.out.println(productDetailDTO);
        System.out.println();
        json = gson.toJson(productDetailDTO);
        System.out.println(json);
        System.out.println();


        // 3번 문제 : 주문 확인 상세 화면 (or2)
        // 틀렸음 : TempDTO 담기
        List<TempDTO.OrderOptionDTO> orderOptionDTOs = new ArrayList<>();
        orderOptionDTOs.add(
                new TempDTO.OrderOptionDTO(
                        orOption4.getId(),
                        orOption4.getOptionName(),
                        orOption4.getQty(),
                        orOption4.getTotalPrice()
                )
        );
        int totalPrice = orOption4.getTotalPrice();
        TempDTO tempDTO = new TempDTO(
                or2.getId(),
                orOption4.getProduct().getId(),
                orderOptionDTOs,
                totalPrice
        );
        System.out.println(tempDTO);
        System.out.println();
        json = gson.toJson(tempDTO);
        System.out.println(json);
        System.out.println();


        // 4번 문제 : 주문 확인 상세 화면 (or1)
        // (orOption1, orOption2), (orOption3) -> OrderDetailDTO
        List<OrderDetailDTO.ProductDTO.ProductOptionDTO> product1OptionDTOs = new ArrayList<>();
        product1OptionDTOs.add(
                new OrderDetailDTO.ProductDTO.ProductOptionDTO(
                        orOption1.getId(),
                        orOption1.getOptionName(),
                        orOption1.getQty(),
                        orOption1.getTotalPrice()
                )
        );
        product1OptionDTOs.add(
                new OrderDetailDTO.ProductDTO.ProductOptionDTO(
                        orOption2.getId(),
                        orOption2.getOptionName(),
                        orOption2.getQty(),
                        orOption2.getTotalPrice()
                )
        );
        List<OrderDetailDTO.ProductDTO.ProductOptionDTO> product2OptionDTOs = new ArrayList<>();
        product2OptionDTOs.add(
                new OrderDetailDTO.ProductDTO.ProductOptionDTO(
                        orOption3.getId(),
                        orOption3.getOptionName(),
                        orOption3.getQty(),
                        orOption3.getTotalPrice()
                )
        );
        List<OrderDetailDTO.ProductDTO> oDProductDTOS = new ArrayList<>();
        oDProductDTOS.add(
                new OrderDetailDTO.ProductDTO(
                        p1.getId(),
                        product1OptionDTOs
                )
        );
        oDProductDTOS.add(
                new OrderDetailDTO.ProductDTO(
                        p2.getId(),
                        product2OptionDTOs
                )
        );
        int sumPrice = orOption1.getTotalPrice() + orOption2.getTotalPrice() + orOption3.getTotalPrice();
        OrderDetailDTO orderDetailDTO = new OrderDetailDTO(or1.getId(), sumPrice, oDProductDTOS);
        System.out.println(orderDetailDTO);
        System.out.println();
        json = gson.toJson(orderDetailDTO);
        System.out.println(json);

    }
}
