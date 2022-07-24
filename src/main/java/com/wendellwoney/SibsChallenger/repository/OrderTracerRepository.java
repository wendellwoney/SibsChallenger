package com.wendellwoney.SibsChallenger.repository;

import com.wendellwoney.SibsChallenger.model.OrderTracer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderTracerRepository extends JpaRepository<OrderTracer, Long> {

}
