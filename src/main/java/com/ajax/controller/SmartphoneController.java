package com.ajax.controller;

import com.ajax.model.SmartPhone;
import com.ajax.service.ISmartPhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/smartphones")
public class SmartphoneController {
    @Autowired
    private ISmartPhoneService smartPhoneService;
    @GetMapping
    public ResponseEntity<Iterable<SmartPhone>> listSmartphone(){
        return new ResponseEntity<>(smartPhoneService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SmartPhone> crateSmartphone(@RequestBody SmartPhone smartPhone) {
        return new ResponseEntity<>(smartPhoneService.save(smartPhone), HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<SmartPhone> deleteSmartphone(@PathVariable Long id){
        Optional<SmartPhone> smartPhoneOptional= smartPhoneService.findById(id);
        if(!smartPhoneOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        smartPhoneService.remove(id);
        return new ResponseEntity<>(smartPhoneOptional.get(), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<SmartPhone> updateSmartphone(@PathVariable Long id, @RequestBody SmartPhone smartPhone) {
        Optional<SmartPhone> smartPhoneOptional = smartPhoneService.findById(id);
        if(!smartPhoneOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
       smartPhone.setId(smartPhoneOptional.get().getId());
        return new ResponseEntity<>(smartPhoneService.save(smartPhone), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<SmartPhone>getSmartphoneById(@PathVariable Long id) {
        Optional<SmartPhone> smartPhoneOptional = smartPhoneService.findById(id);
        if (!smartPhoneOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(smartPhoneOptional.get());
    }


}

