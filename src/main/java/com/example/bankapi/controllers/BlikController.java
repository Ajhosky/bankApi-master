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

    @GetMapping("blik/createCode/{token}")
    public String generateBlikCode(@PathVariable("token") String token){

        return blikRepository.generateBlikCode(token);
    }

    @GetMapping("blik/{code}/{price}")
    public String changePrice(@PathVariable("code") String code, @PathVariable("price") String priceS){
        float price = Float.parseFloat(priceS);
        blikRepository.changePrice(code, price);
        return "ok";
    }

    @GetMapping("blik/token/{code}")
    public String getToken(@PathVariable("code") String code){
        return blikRepository.checkToken(code);
    }

    @GetMapping("blik/price/{code}")
    public String getPrice(@PathVariable("code") String code){
        return blikRepository.getPrice(code);
    }
}
