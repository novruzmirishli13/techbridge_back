package com.example.HZT.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FavController {
     @RequestMapping("favicon.ico")
    public void favicon() {
    }
}
