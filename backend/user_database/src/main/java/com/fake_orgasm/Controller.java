package com.fake_orgasm;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class Controller {

    @GetMapping("/dummy")
    public String dummy(){
     return "Dummy String";
    }
}
