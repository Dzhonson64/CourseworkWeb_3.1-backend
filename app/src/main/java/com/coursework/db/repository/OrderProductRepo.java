package com.coursework.db.repository;

import com.coursework.db.model.OrderProductEntity;
import com.coursework.db.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderProductRepo extends JpaRepository<OrderProductEntity, Long> {
    @Query(value = " SELECT op " +
            " FROM OrderProductEntity op " +
            " INNER JOIN OrderEntity o ON " +
            " op.order = o " +
            " WHERE o.user = :user ")
    List<OrderProductEntity> getAllOrdersByUserId(@Param("user") UserEntity user);
}
