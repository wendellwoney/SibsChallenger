package com.wendellwoney.SibsChallenger.repository;

import com.wendellwoney.SibsChallenger.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o FROM Order o WHERE o.completed = false ORDER BY o.createdAt ASC")
    public List<Order> ordersNotCompleted();

    @Query("UPDATE Order set completed = true where id = ?1")
    public Order setCompleted(Long id);
}
