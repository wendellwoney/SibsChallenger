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
@Table(name = "itens")
@SQLDelete(sql = "UPDATE itens SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Item extends BaseEntity {

    public Item(Long id) {
        this.setId(id);
    }

    @Column(nullable = false)
    private String name;

    @Lob
    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "item")
    private List<StockMovement> stockMoviments;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (!name.equals(item.name)) return false;
        return description.equals(item.description);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + description.hashCode();
        return result;
    }
}
