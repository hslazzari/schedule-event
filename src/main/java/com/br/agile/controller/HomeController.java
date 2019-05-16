package com.br.agile.controller;

import java.io.ByteArrayOutputStream;
import java.util.Map;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.br.agile.bean.User;
import com.br.agile.repository.UserRepository;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

@Controller
public class HomeController {
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/")
    public String home1() {
		return "/home";
    }
	
  
    @GetMapping("/login")
    public String login() {
    	return "login"; 
    }


    @GetMapping("/403")
    public String error403() {
        return "/error/403";
    }

}
