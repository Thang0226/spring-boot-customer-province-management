package com.repository;

import com.model.Province;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProvinceRepository extends CrudRepository<Province, Long> {

    @Query(nativeQuery = true, value="call delete_province(:id)")
    void deleteProvince(Long id);
}
