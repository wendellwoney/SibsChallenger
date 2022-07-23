package com.wendellwoney.SibsChallenger.repository;

import com.wendellwoney.SibsChallenger.model.StockMovement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockMovementRepository extends JpaRepository<StockMovement, Long> {
}
