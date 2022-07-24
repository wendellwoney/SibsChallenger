package com.wendellwoney.SibsChallenger.model;

import com.wendellwoney.SibsChallenger.configuration.OrderStatusEnum;
import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
@SQLDelete(sql = "UPDATE orders SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Order extends BaseEntity implements Cloneable {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @OneToMany(mappedBy = "order")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<OrderItem> orderItens;

    @OneToMany(mappedBy = "order")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<OrderTracer> orderTracers;

    @Enumerated(EnumType.ORDINAL)
    private OrderStatusEnum status;

    private String note;

    public Order clone() throws CloneNotSupportedException
    {
        return (Order) super.clone();
    }
}
