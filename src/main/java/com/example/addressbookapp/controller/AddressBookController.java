package com.example.addressbookapp.controller;

import com.example.addressbookapp.dto.AddressBookDTO;
import com.example.addressbookapp.dto.ResponseDTO;
import com.example.addressbookapp.model.AddressBook;
import com.example.addressbookapp.service.IAddressBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class AddressBookController {
    @Autowired
    IAddressBookService iAddressBookService;

    List<AddressBook> addrDatalist = new ArrayList<>();

    //ResponseEntity check Status and store in json format
    @RequestMapping(value = {"","/", "/get"})
    public ResponseEntity<ResponseDTO> getAddressBookData() {
        addrDatalist = iAddressBookService.getAddressBookData();
        ResponseDTO respOTO = new ResponseDTO("Get Call Successful", addrDatalist);
        return new ResponseEntity<ResponseDTO>(respOTO, HttpStatus.OK);
    }
    @GetMapping("/get/{empId}")
    public ResponseEntity<ResponseDTO> getAddressBookData(@PathVariable("empId") int empID) {
        AddressBook empData= null;
        empData = iAddressBookService.getAddressBookDataById(empID);
        ResponseDTO respDTO= new ResponseDTO("Get Call For ID Successful", empData);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseDTO> createAddressBookData(
            @Valid @RequestBody AddressBookDTO addrDTO) {
        AddressBook empData = iAddressBookService.createAddressBookData(addrDTO);
        ResponseDTO respOTO= new ResponseDTO("Created AddressBook  Data Successfully", empData);
        return new ResponseEntity<ResponseDTO>(respOTO, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDTO> updateAddressBookData(@PathVariable int id,@Valid @RequestBody AddressBookDTO addrDTO) {
        ResponseDTO respDTO= new ResponseDTO("Updated AddressBook Details Successfully", iAddressBookService.updateAddressBookData(id,addrDTO));
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{Id}")
    public ResponseEntity <ResponseDTO> deleteAddressBookData(@PathVariable("Id") int Id) {
        iAddressBookService.deleteAddressBookData(Id);
        ResponseDTO respDTO= new ResponseDTO("Deleted Successfully", "Deleted id: "+Id);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }

    @GetMapping("/city/{city}")
    public ResponseEntity<ResponseDTO> getCityAddressBookData(@PathVariable("city") String city) {
        AddressBook empDatalist = iAddressBookService.findByCity (city);
        ResponseDTO respOTO= new ResponseDTO("Get Call For ID Successful", empDatalist);
        return new ResponseEntity<ResponseDTO>(respOTO, HttpStatus.OK);
    }
    @GetMapping("/state/{state}")
    public ResponseEntity<ResponseDTO> getStateAddressBookData(@PathVariable("state") String state) {
        AddressBook empDatalist = iAddressBookService.findByState(state);
        ResponseDTO respOTO= new ResponseDTO("Get Call For ID Successful", empDatalist);
        return new ResponseEntity<ResponseDTO>(respOTO, HttpStatus.OK);
    }
    @GetMapping(value = "/sortCity")
    public List<AddressBook> getCitiesByName() {
        return iAddressBookService.findAllOrderByNameAsc();
    }

    @GetMapping(value = "/sortState")
    public List<AddressBook> getStateByName() {
        return iAddressBookService.findAllOrderByStateNameAsc();
    }


}
