package com.coursework.db.repository;

import com.coursework.db.model.TypeProductEntity;
import com.coursework.web.dto.type.CatalogType;
import com.coursework.web.dto.type.StatusActiveType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeProductRepo extends JpaRepository<TypeProductEntity, Long> {
    List<TypeProductEntity> findAllByTypeAndStatus(CatalogType type, StatusActiveType status);
    List<TypeProductEntity> findAllByStatus(StatusActiveType status);
    List<TypeProductEntity> findAllByTypeProductList(List<TypeProductEntity> list);
    @Modifying
    @Query(value = " DELETE FROM public.type_product t WHERE t.status = 'UNABLE' ", nativeQuery = true)
   int deleteUnable();
}
