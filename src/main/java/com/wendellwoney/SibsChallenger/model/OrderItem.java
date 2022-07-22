package com.wendellwoney.SibsChallenger.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_itens")
public class OrderItem extends BaseEntity {

    @ManyToOne
    @JoinColumn(name="order_id", nullable=false)
    private Order order;

    @ManyToOne
    @JoinColumn(name="item_id", nullable=false)
    private Item item;

    @Column(nullable = false, columnDefinition = "Decimal(10,2)")
    private Double quantity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderItem orderItem = (OrderItem) o;

        if (!order.equals(orderItem.order)) return false;
        if (!item.equals(orderItem.item)) return false;
        return quantity.equals(orderItem.quantity);
    }

    @Override
    public int hashCode() {
        int result = order.hashCode();
        result = 31 * result + item.hashCode();
        result = 31 * result + quantity.hashCode();
        return result;
    }
}
