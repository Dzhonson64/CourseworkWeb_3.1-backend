package com.coursework.db.repository;

import com.coursework.db.model.TypePropertyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypePropertyRepo extends JpaRepository<TypePropertyEntity, Long> {
}
