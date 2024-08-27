package com.ajax.repository;

import com.ajax.model.SmartPhone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISmartphoneRepository extends JpaRepository<SmartPhone,Long> {

}
