package com.wendellwoney.SibsChallenger.repository;

import com.wendellwoney.SibsChallenger.dto.Stock;
import com.wendellwoney.SibsChallenger.model.StockMovement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockMovementRepository extends JpaRepository<StockMovement, Long> {
    @Query(value = "SELECT s.id, s.item.id, COALESCE((s.quantity - SUM(t.quantityUsed)), s.quantity) as quantity FROM " +
            "StockMovement s LEFT JOIN OrderTracer t on (s.id = t.stockMovement.id) " +
            "GROUP BY s.id, s.item.id ORDER BY s.item.id, s.id")
    List<Object[]> getStockProduct();
}
