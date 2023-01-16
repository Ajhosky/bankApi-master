package com.example.bankapi.controllers;

import com.example.bankapi.models.BlikModel;
import com.example.bankapi.models.ProductModel;
import com.example.bankapi.repositories.BlikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BlikController {
    @Autowired
    BlikRepository blikRepository;
    // dodac get mapping by zwracal rekord po kodzie ean

    @GetMapping("blik")
    public String test(){
        return "hello";
    }

    @GetMapping("blik/codes")
    public List<BlikModel> showBliks(){
        return blikRepository.getBlikCodes();

    }

    @GetMapping("blik/{code}")
    public String checkBlikCode(@PathVariable("code") String code){
        return blikRepository.checkBlikCode(code);

    }

    @GetMapping("blik/createCode")
    public String generateBlikCode(){

        return blikRepository.generateBlikCode();
    }

    @PatchMapping("blik/{code}")
    public void changePrice(@PathVariable("code") String code, @RequestBody BlikModel updatedBlik){
        blikRepository.changePrice(code, updatedBlik);
    }
}
