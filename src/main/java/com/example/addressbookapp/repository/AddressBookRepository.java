package com.example.addressbookapp.repository;

import com.example.addressbookapp.model.AddressBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressBookRepository extends JpaRepository<AddressBook,Integer> {
@Query(value = "select * from address_book where id=id and city = :city",nativeQuery = true)
AddressBook findByCity(@Param("city") String city);

@Query(value = "select * from address_book where id=id and state = :state",nativeQuery = true)
AddressBook findByState(@Param("state") String state);
}
