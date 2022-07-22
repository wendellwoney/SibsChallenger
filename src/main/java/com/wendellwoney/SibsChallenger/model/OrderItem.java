package com.wendellwoney.SibsChallenger.model;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_itens")
public class OrderItem extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="order_id", nullable=false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="item_id", nullable=false)
    private Item item;

    @Column(nullable = false)
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
