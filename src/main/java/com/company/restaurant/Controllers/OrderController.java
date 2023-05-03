package com.company.restaurant.Controllers;

import com.company.restaurant.DTO.AjaxResponseBody;
import com.company.restaurant.Models.Order;
import com.company.restaurant.Repositories.OrderRepository;
import com.company.restaurant.Services.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.Arrays;


@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderRepository orderRepository;


    private static final String emoji = "🍏 🍎 🍐 🍊 🍋 🍌 🍉 🍇 🍓 🍈 🍒 🍑 🥭 🍍 🥥 🥝 🍅 🍆 🥑 🥦 🥬 🥒 🌶 🌽 🥕 🧄 🧅 🥔 🍠 🥐 🥯 🍞 🥖 🥨 🧀 🥚 🍳 🧈 🥞 🧇 🥓 🥩 🍗 🍖 🦴 🌭 🍔 🍟 🍕 🥪 🥙 🧆 🌮 🌯 🥗 🥘 🥫 🍝 🍜 🍲 🍛 🍣 🍱 🥟 🦪 🍤 🍙 🍚 🍘 🍥 🥠 🥮 🍢 🍡 🍧 🍨 🍦 🥧 🧁 🍰 🎂 🍮 🍭 🍬 🍫 🍿 🍩 🍪 🌰 🥜 🍯 🥛 🍼 ☕ 🍵 🧃 🥤 🍶 🍺 🍻 🥂 🍷 🥃 🍸 🍹 🧉 🍾 🧊 🥄 🍴 🍽 🥣 🥡 🥢 🧂";


    @GetMapping("/orders")
    public String list(Principal principal, Model model) {
        Iterable<Order> orders = orderService.getMyOrder(principal.getName());
        model.addAttribute("orders", orders);
        model.addAttribute("emoji", Arrays.asList(emoji.split("\\s+")));
        return "order-page";

    }

    @GetMapping("/orders/view/{id}")
    public String getMyOrder(Principal principal, Model model, @PathVariable(name = "id") Order order) {
        if (!order.getUser().getUsername().equals(principal.getName())) {
            return "redirect:/orders";
        }
        model.addAttribute("o", order);
        return "orders-details";

    }

    @GetMapping("/orders/status/{id}")
    public @ResponseBody
    ResponseEntity<?> getMyOrderStatus(Principal principal, @PathVariable(name = "id") Long id) {
        Order order = orderRepository.findByIdAndUserUsername(id, principal.getName());

        if (!order.getUser().getUsername().equals(principal.getName())) {
            return ResponseEntity.badRequest().body(new AjaxResponseBody("ERROR"));
        }
        return ResponseEntity.badRequest().body(order);
    }

    @PostMapping("/orders/remove/{order}")
    public String deleteOrder(Principal principal, @PathVariable(name = "order") Long id) {
        orderService.deleteTheOrder(id, principal.getName());
        return "redirect:/orders";
    }

}
