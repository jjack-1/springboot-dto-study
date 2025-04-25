package ex01;

import lombok.Data;

@Data
public class ProductDTO {
    private int id;
    private String name;

    public ProductDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
