package com.company.restaurant.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/verify")
public class VerificationController {

    @GetMapping
    public String verifyPage(Model model) {
        return "verify-page";
    }

}
