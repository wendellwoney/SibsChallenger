package com.wendellwoney.SibsChallenger.repository;

import com.wendellwoney.SibsChallenger.model.Order;
import com.wendellwoney.SibsChallenger.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
