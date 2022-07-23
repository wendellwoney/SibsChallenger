package com.wendellwoney.SibsChallenger.repository;

import com.wendellwoney.SibsChallenger.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
