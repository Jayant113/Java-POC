package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ResponseDto;
import com.app.pojos.Address;
import com.app.service.IAddressService;
import com.app.service.IUserService;

@RestController
@CrossOrigin
@RequestMapping("/address")
public class AddressController {
	
	@Autowired
	private IAddressService addrService;
	
	@Autowired
	private IUserService userService;
	
	@GetMapping("/all")
	public ResponseEntity<?> getAllAddresses(){
		Integer userId =Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getName());
		return new ResponseEntity<>(new ResponseDto<List<Address>>("success", addrService.getAllAddressesByUserId(userId)), HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> addNewAddress(@RequestBody Address addr){
		Integer userId =Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getName());
		addr.setSelectedUser(userService.findById(userId));
		return new ResponseEntity<>(new ResponseDto<Address>("success", addrService.addOrEditAddress(addr)),HttpStatus.CREATED);
	}
	
	@PutMapping("/edit/{addrId}")
	public ResponseEntity<?> editAddressById(@RequestBody Address addr,@PathVariable Integer addrId){
		Integer userId =Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getName());
		addr.setSelectedUser(userService.findById(userId));
		addr.setId(addrId);
		return new ResponseEntity<>(new ResponseDto<Address>("success", addrService.addOrEditAddress(addr)),HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{addrId}")
	public ResponseEntity<?> deleteAddressById(@PathVariable Integer addrId){
		return new ResponseEntity<>(new ResponseDto<String>("success", addrService.deleteAddressById(addrId)),HttpStatus.OK);
	}
}