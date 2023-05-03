package com.company.restaurant.Controllers;


import com.company.restaurant.Configuration.SecurityServiceImpl;
import com.company.restaurant.Models.Food;
import com.company.restaurant.Models.Order;
import com.company.restaurant.Models.Usr;
import com.company.restaurant.Repositories.FoodRepository;
import com.company.restaurant.Repositories.OrderRepository;
import com.company.restaurant.Repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;


@Controller
public class MainController {

    @Autowired
    private final UsersRepository usersRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final FoodRepository foodRepository;
    @Autowired
    private final OrderRepository orderRepository;

    private SecurityServiceImpl securityService;

    public MainController(UsersRepository usersRepository, PasswordEncoder passwordEncoder, FoodRepository foodRepository, OrderRepository orderRepository) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
        this.foodRepository = foodRepository;
        this.orderRepository = orderRepository;

    }

    @GetMapping("/")
    public String MainPage(Model model) {
        Iterable<Food> foods = foodRepository.findAll();
        List<Food> foodList=new ArrayList<>();
        foods.forEach(foodList::add);
        model.addAttribute("first", foodList.get(0));
        model.addAttribute("second", foodList.get(2));
        model.addAttribute("third", foodList.get(4));
        return "main-page";
    }

    @GetMapping("/login")
    public String LoginPage(Model model) {
        return "login-page";
    }

    @GetMapping("/users")
    public String users(Model model) {
        Iterable<Usr> users = usersRepository.findAll();
        model.addAttribute("users", users);
        return "users-page";
    }

    @GetMapping("/profile")
    public String profilePage(Model model, Principal principal) {

        return getString(model, principal);
    }


    @GetMapping("/users/add")
    public String addUserPage() {
        return "add-user";
    }

    @PostMapping("/users/add")
    public String addUserAction(@RequestParam String txtUsername,
                                @RequestParam String txtPassword,
                                @RequestParam String role,
                                @RequestParam String permission) {
        Usr user = new Usr(txtUsername, passwordEncoder.encode(txtPassword), role, permission);
        usersRepository.save(user);
//        securityService.autoLogin(user.getUsername(),user.getPassword());

        return "redirect:/catalog";
    }

    @GetMapping("/about")
    public String aboutPage() {
        return "about-page";
    }

    private String getString(Model model, Principal principal) {
        Usr auth = usersRepository.findByUsername(principal.getName());
        String role = auth.getRoles();
        model.addAttribute("authuser", principal.getName());

        List<Order> myOrders = orderRepository.findMyPaidOrDoneOrders(principal.getName());
        DoubleSummaryStatistics doubleSummaryStatistics = myOrders.stream().collect(Collectors.summarizingDouble(Order::getTotalOrderPrice));


        model.addAttribute("statistics", doubleSummaryStatistics);
        model.addAttribute("role", role);
        return "profile-page";
    }

}


