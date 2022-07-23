package com.wendellwoney.SibsChallenger.repository;

import com.wendellwoney.SibsChallenger.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
