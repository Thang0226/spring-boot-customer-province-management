package com.repository;

import com.model.DTO.ProvinceDTO;
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

    @Query(nativeQuery = true, value="select p.id, p.name, count(*) count from province p left join customer c on p.id = c.province_id group by p.id")
    Iterable<ProvinceDTO> countCustomerByProvince();
}
