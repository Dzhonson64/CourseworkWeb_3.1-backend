package com.coursework.db.repository;

import com.coursework.db.model.OrderEntity;
import com.coursework.db.model.ProviderEntity;
import com.coursework.db.model.UserEntity;
import com.coursework.db.model.type.Status;
import org.apache.tomcat.jni.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepo extends JpaRepository<OrderEntity, Long> {
    @Query(value = " SELECT orders.* FROM waybill " +
            " INNER JOIN product " +
            " ON product.id = waybill.product_id " +
            " INNER JOIN order_product " +
            " ON order_product.product_id = product.id " +
            " INNER JOIN orders " +
            " ON orders.id = order_product.order_id " +
            " WHERE waybill.provider_id = :id ", nativeQuery = true)
    List<OrderEntity> findByProvider(@Param("id") Long id);

    Optional<OrderEntity> findByStatusAndUser(Status status, UserEntity user);
    List<OrderEntity> findAllByUser(UserEntity user);
}
