package com.repository;

import com.model.Province;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IProvinceRepository extends CrudRepository<Province, Long> {

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value="call delete_province(:id)")
    void deleteProvince(@Param("id") Long id);
}
