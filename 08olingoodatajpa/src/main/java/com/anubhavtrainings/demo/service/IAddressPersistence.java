package com.anubhavtrainings.demo.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anubhavtrainings.demo.entities.Address;

public interface IAddressPersistence extends JpaRepository<Address, String> {

}
