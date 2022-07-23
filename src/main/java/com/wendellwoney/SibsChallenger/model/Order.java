package com.wendellwoney.SibsChallenger.model;

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

    private Boolean completed = false;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (!user.equals(order.user)) return false;
        if (!orderItens.equals(order.orderItens)) return false;
        if (orderTracers != null ? !orderTracers.equals(order.orderTracers) : order.orderTracers != null) return false;
        return completed != null ? completed.equals(order.completed) : order.completed == null;
    }

    @Override
    public int hashCode() {
        int result = user.hashCode();
        result = 31 * result + orderItens.hashCode();
        result = 31 * result + (orderTracers != null ? orderTracers.hashCode() : 0);
        result = 31 * result + (completed != null ? completed.hashCode() : 0);
        return result;
    }

    public Order clone() throws CloneNotSupportedException
    {
        return (Order) super.clone();
    }
}
