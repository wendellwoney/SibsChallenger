package com.wendellwoney.SibsChallenger.model;

import com.wendellwoney.SibsChallenger.configuration.mapper.StockMovementMapper;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
@SQLDelete(sql = "UPDATE orders SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Order extends BaseEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItens;

    @OneToMany(mappedBy = "order")
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
}
