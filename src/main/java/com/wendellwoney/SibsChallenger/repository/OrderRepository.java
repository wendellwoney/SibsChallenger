package com.wendellwoney.SibsChallenger.repository;

import com.wendellwoney.SibsChallenger.model.Order;
import com.wendellwoney.SibsChallenger.configuration.OrderStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o FROM Order o WHERE o.status = com.wendellwoney.SibsChallenger.configuration.OrderStatusEnum.INPROGESS ORDER BY o.createdAt ASC")
    List<Order> ordersNotCompleted();

    @Modifying
    @Query("UPDATE Order set status = ?1 where id = ?2")
    @Transactional
    void setStatus(OrderStatusEnum status, Long id);

    @Modifying
    @Query("UPDATE Order set status = ?1, note = ?2 where id = ?3")
    @Transactional
    void cancel(OrderStatusEnum status, String note, Long id);
}
