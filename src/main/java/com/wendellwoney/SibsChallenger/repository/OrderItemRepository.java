package com.wendellwoney.SibsChallenger.repository;

import com.wendellwoney.SibsChallenger.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
