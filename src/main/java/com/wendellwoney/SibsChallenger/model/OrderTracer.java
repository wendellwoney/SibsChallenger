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
@Table(name = "order_tracers")
public class OrderTracer extends BaseEntity{

    @ManyToOne
    @JoinColumn(name="order_id", nullable=false)
    private Order order;

    @ManyToOne
    @JoinColumn(name="stockmovement_id", nullable=false)
    private StockMovement stockMovement;

    @Column(name="quantity_used",nullable = false, columnDefinition = "Decimal(10,2)")
    private Double quantityUsed;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderTracer that = (OrderTracer) o;

        if (!order.equals(that.order)) return false;
        if (!stockMovement.equals(that.stockMovement)) return false;
        return quantityUsed.equals(that.quantityUsed);
    }

    @Override
    public int hashCode() {
        int result = order.hashCode();
        result = 31 * result + stockMovement.hashCode();
        result = 31 * result + quantityUsed.hashCode();
        return result;
    }
}
