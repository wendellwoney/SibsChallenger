package com.wendellwoney.SibsChallenger.repository;

import com.wendellwoney.SibsChallenger.model.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository extends JpaRepository<BaseEntity, Long> {
}
