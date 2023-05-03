package com.company.restaurant.Controllers;

import com.company.restaurant.Methods.StringConfigurerMethods;
import com.company.restaurant.Models.DiningTables;
import com.company.restaurant.Models.Food;
import com.company.restaurant.Repositories.DiningTablesRepository;
import com.company.restaurant.Repositories.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class FoodController {
    @Autowired
    private final FoodRepository foodRepository;
    @Autowired
    private final DiningTablesRepository diningTablesRepository;

    public FoodController(FoodRepository foodRepository, DiningTablesRepository diningTablesRepository) {
        this.foodRepository = foodRepository;
        this.diningTablesRepository = diningTablesRepository;
    }

    @GetMapping("/order/{id}")
    public String qrCodeGenerator(Model model, @PathVariable("id") Long id) {
        if (!foodRepository.existsByID(id)) {
            return "redirect:/";
        }
        Food food = foodRepository.findByID(id);
        model.addAttribute("food", food.toString());
        return "QrCodeGen";
    }

    @GetMapping("/food/add")
    public String addFood() {
        return "add-food";
    }

    @PostMapping("/food/add")
    public String addFoodAction(@RequestParam String category,
                                @RequestParam String nme,
                                @RequestParam String description,
                                @RequestParam long price,
                                @RequestParam String size,
                                @RequestParam String image) {
        Food food=new Food();
        food.setName(nme);
        food.setSizes(size);
        food.setPrice(price);
        food.setDescription(description);
        food.setCategory(category);
        food.setImageUrl(image);
        foodRepository.save(food);

        return "redirect:/";

    }

    @GetMapping("/{category}/{name}")
    public String detailedViewFood(Model model,
                                   @PathVariable(value = "category") String category,
                                   @PathVariable(value = "name") String name) {
        if (!foodRepository.existsByCategoryAndName(category, name)) {
            return "redirect:/";
        }
        Food food = foodRepository.findFoodByCategoryAndName(category, name);
        model.addAttribute("food", food);

        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String strDate = dateFormat.format(date);

        model.addAttribute("date", strDate);

        return "detailed-view-food";

    }

    @GetMapping("/catalog")
    public String catalogPagewithChair(@RequestParam(name = "chair", required = false, defaultValue = "0") Long chair_id, Model model) {
        getCatalog(chair_id, model);
        return "catalog-page";

    }

    private void getCatalog(Long chair_id, Model model) {
        Iterable<Food> foodList = foodRepository.findAll();
        List<String> categories = foodRepository.findAllCategories();
        List<String> colors = StringConfigurerMethods.allColorsinBootstrap();

        model.addAttribute("foodList", foodList);

        DiningTables diningTables = diningTablesRepository.findByID(chair_id);
        if (diningTables != null) {
            model.addAttribute("chair", diningTables);
        }
        model.addAttribute("categories", categories);
        model.addAttribute("colors", colors);
    }


}
