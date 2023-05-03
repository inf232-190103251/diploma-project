package com.company.restaurant.Controllers;

import com.company.restaurant.DTO.ChargeRequest;
import com.company.restaurant.Models.Order;
import com.company.restaurant.Repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class CheckoutController {

    @Value("${STRIPE_PUBLIC_KEY}")
    private String stripePublicKey;

    @Autowired
    private final OrderRepository orderRepository;

    public CheckoutController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @RequestMapping("/checkout/{order_id}")
    public String checkout(Model model, @PathVariable(name = "order_id") Long order_id, Principal principal) {

        Order check = orderRepository.findByIdAndUserUsername(order_id, principal.getName());
        if (check == null) {
            return "redirect:/";
        }
        int price = (int) (check.getTotalOrderPrice() / 1);
        if (price >= 3000) {
            price *= 0.9;
            System.out.println("Price with discount : " + price);
            model.addAttribute("discount", 10);
        }
        model.addAttribute("amount", price); // in kzt
        model.addAttribute("orderid", check.getId());
        model.addAttribute("stripePublicKey", stripePublicKey);
        model.addAttribute("currency", ChargeRequest.Currency.KZT);
        return "checkout";
    }
}