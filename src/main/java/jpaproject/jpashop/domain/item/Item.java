package jpaproject.jpashop.domain.item;

import jpaproject.jpashop.domain.Category;
import jpaproject.jpashop.exception.NotEnoughStockException;
import jpaproject.jpashop.repository.ItemRepository;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    // 비즈니스 로직
    public void addStock(int quantity){
        this.stockQuantity += quantity;
    }

    public void removeStock(int quantity){
        int restStock = this.stockQuantity - quantity;
        if(restStock < 0){
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }

    public void updateItem(Long itemId,String name, int price,int stockQuantity){
        this.id = itemId;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

}
