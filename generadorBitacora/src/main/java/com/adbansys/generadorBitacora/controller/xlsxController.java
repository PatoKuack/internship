package com.adbansys.generadorBitacora.controller;

import com.adbansys.generadorBitacora.xlsx.Bitacora;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bitacora")
public class xlsxController {

	@GetMapping("/")
    public String inicio() {
        return "hi";
    }
    
}
