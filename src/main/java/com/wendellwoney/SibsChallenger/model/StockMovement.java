package com.wendellwoney.SibsChallenger.model;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "stock_movements")
@SQLDelete(sql = "UPDATE stock_movements SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class StockMovement extends BaseEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="item_id", nullable=false)
    private Item item;

    @Column(nullable = false)
    private Double quantity;

    @OneToMany(mappedBy = "stockMovement")
    private List<OrderTracer> orderTracers;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StockMovement that = (StockMovement) o;

        if (!item.equals(that.item)) return false;
        if (!quantity.equals(that.quantity)) return false;
        return orderTracers != null ? orderTracers.equals(that.orderTracers) : that.orderTracers == null;
    }

    @Override
    public int hashCode() {
        int result = item.hashCode();
        result = 31 * result + quantity.hashCode();
        result = 31 * result + (orderTracers != null ? orderTracers.hashCode() : 0);
        return result;
    }
}
