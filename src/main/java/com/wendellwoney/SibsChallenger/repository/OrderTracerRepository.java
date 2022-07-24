package com.wendellwoney.SibsChallenger.repository;

import com.wendellwoney.SibsChallenger.configuration.OrderStatusEnum;
import com.wendellwoney.SibsChallenger.model.OrderTracer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface OrderTracerRepository extends JpaRepository<OrderTracer, Long> {
    @Modifying
    @Query("DELETE FROM OrderTracer where order.id = ?1")
    @Transactional
    void cancelOrder(Long idOrder);
}
