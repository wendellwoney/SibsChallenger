package com.wendellwoney.SibsChallenger.model;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
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

}
