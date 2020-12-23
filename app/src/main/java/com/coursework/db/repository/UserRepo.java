package com.coursework.db.repository;

import com.coursework.db.model.UserEntity;
import feign.Param;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String name);

    @Modifying      // to mark delete or update query
    @Query(value = "DELETE FROM UserEntity p WHERE p.id = :id")
    int delete(@Param("id") Long id);
}
