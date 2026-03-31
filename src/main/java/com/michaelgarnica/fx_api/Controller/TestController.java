package com.michaelgarnica.fx_api.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/")
    public String sayHelloWorld(){
        return "HELLO WORLS. THIS SHIT IS RUNNNING!!!!";
    }

    @GetMapping("/test")
    public String testEnpoint(){
        return "THIS IS THE TEST THAT IS WORIKING 3.0!!";
    }
}
