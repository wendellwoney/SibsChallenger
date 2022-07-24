package com.wendellwoney.SibsChallenger.model;

import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_tracers")
@SQLDelete(sql = "UPDATE order_tracers SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class OrderTracer extends BaseEntity{

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="order_id", nullable=false)
    private Order order;

    @ManyToOne
    @JoinColumn(name="orderitem_id", nullable=false)
    @LazyCollection(LazyCollectionOption.FALSE)
    private OrderItem orderItem;

    @ManyToOne
    @JoinColumn(name="stockmovement_id", nullable=false)
    @LazyCollection(LazyCollectionOption.FALSE)
    private StockMovement stockMovement;

    @Column(name="quantity_used",nullable = false)
    private Double quantityUsed;

}
