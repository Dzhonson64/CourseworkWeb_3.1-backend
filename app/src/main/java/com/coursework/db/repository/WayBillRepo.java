package com.coursework.db.repository;

import com.coursework.db.model.WaybillEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WayBillRepo extends JpaRepository<WaybillEntity, Long> {
}
