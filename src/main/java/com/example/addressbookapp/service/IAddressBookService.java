package com.example.addressbookapp.service;

import com.example.addressbookapp.dto.AddressBookDTO;
import com.example.addressbookapp.model.AddressBook;

import java.util.List;

public interface IAddressBookService {

    List<AddressBook> getAddressBookData();

    AddressBook  getAddressBookDataById(int empId);

    AddressBook createAddressBookData(AddressBookDTO AddressBookDTO);

    AddressBook updateAddressBookData(int empId, AddressBookDTO AddressBookDTO);

    String deleteAddressBookData(int empID);

    AddressBook findByCity(String city);
    AddressBook findByState(String state);
    List<AddressBook> findAllOrderByNameAsc();
    List<AddressBook> findAllOrderByStateNameAsc();
}
