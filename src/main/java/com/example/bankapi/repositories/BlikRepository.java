package com.example.bankapi.repositories;

import com.example.bankapi.models.BlikModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class BlikRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<BlikModel> getBlikCodes()
    {
        return
            jdbcTemplate.query(
                    "SELECT * FROM blik",
                    BeanPropertyRowMapper.newInstance(BlikModel.class)
        );

    }

    public String checkBlikCode(String code) {

        try {
            jdbcTemplate.queryForObject(
                    "SELECT code FROM blik WHERE code = "+code,
                    BeanPropertyRowMapper.newInstance(BlikModel.class));
            return "podales dobry kod";
        } catch (EmptyResultDataAccessException e) {
            return "zly kod" + e.getMessage();
        }


        /*if(
                jdbcTemplate.query(
                "SELECT code FROM blik WHERE code = "+code,
                BeanPropertyRowMapper.newInstance(BlikModel.class)
        ) == code){
            return "brawo zaplaciles";

        }else {
            return "zly kod blik";
        }*/

    }

    public String generateBlikCode() {
        String AlphaNumericString = "0123456789";
        LocalDateTime dateNow = LocalDateTime.now();
        LocalDateTime date = dateNow.plusMinutes(2);
        StringBuilder sb = new StringBuilder(6);
        int singleDigit;
        for (int i = 0; i < 6; i++) {
            int index = (int)(AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }
        String code = sb.toString();
        jdbcTemplate.update("INSERT INTO blik (code, price, id_konta, expire_date) VALUES ('"+code+"','0','1','"+date+"')");
        return code;
    }

    public void changePrice(String code, BlikModel updatedBlik){
        BlikModel oldBlik = jdbcTemplate.queryForObject("SELECT code FROM blik WHERE code = " + code, BeanPropertyRowMapper.newInstance(BlikModel.class));
        float price = oldBlik.getPrice();
        jdbcTemplate.update("UPDATE blik price="+price+" WHERE code="+code);
    }
}
