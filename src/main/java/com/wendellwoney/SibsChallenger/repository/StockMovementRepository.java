package com.wendellwoney.SibsChallenger.repository;

import com.wendellwoney.SibsChallenger.dto.Stock;
import com.wendellwoney.SibsChallenger.model.StockMovement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockMovementRepository extends JpaRepository<StockMovement, Long> {
    @Query(value = "SELECT s.id, s.item, COALESCE((s.quantity - SUM(t.quantityUsed)), s.quantity) as quantity FROM " +
            "StockMovement s LEFT JOIN OrderTracer t on (s.id = t.stockMovement.id) WHERE s.item.id IN ?1 " +
            "GROUP BY s.id ORDER BY s.item.id, s.id")
    List<Stock> getStockByProducts(List<Long> productsId);
    @Query(value = "SELECT s.id, s.item, COALESCE((s.quantity - SUM(t.quantityUsed)), s.quantity) as quantity FROM " +
            "StockMovement s LEFT JOIN OrderTracer t on (s.id = t.stockMovement.id) WHERE s.item.id = ?1 " +
            "GROUP BY s.id ORDER BY s.item.id, s.id")
    List<Stock> getStockByProduct(Long product);
}
