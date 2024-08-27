package com.ajax.service;

import com.ajax.model.SmartPhone;
import com.ajax.repository.ISmartphoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SmartphoneService implements ISmartPhoneService {
    @Autowired
    private ISmartphoneRepository iSmartphoneRepository;

    @Override
    public Iterable<SmartPhone> findAll() {
        return iSmartphoneRepository.findAll();
    }

    @Override
    public Optional<SmartPhone> findById(Long id) {
        return iSmartphoneRepository.findById(id);
    }

    @Override
    public SmartPhone save(SmartPhone smartPhone) {
      return iSmartphoneRepository.save(smartPhone);
    }


    @Override
    public void remove(Long id) {
        iSmartphoneRepository.deleteById(id);
    }
}
