package com.repository;

import com.model.Customer;
import com.model.Province;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Long> {
    Iterable<Customer> findAllByProvince(Province province);

    Page<Customer> findAllByFirstNameContaining(Pageable pageable, String name);

    @Query(value="from Customer where concat(firstName, ' ', lastName) like %:text%")
    Page<Customer> findAllByFullNameContaining(String text, Pageable pageable);
}
