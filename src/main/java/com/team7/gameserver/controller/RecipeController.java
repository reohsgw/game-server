package com.team7.gameserver.controller;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/recipes")
@CrossOrigin(origins = "*")
public class RecipeController {

    @GetMapping("/{recipeName}")
    public Map<String, Object> getRecipe(@PathVariable String recipeName) {
        Map<String, Object> response = new HashMap<>();

        switch (recipeName.toLowerCase()) {
            case "omurice":
                response.put("recipeName", "Omurice");
                response.put("country", "Japan");
                response.put("ingredients", Arrays.asList(
                        "Egg",
                        "Rice",
                        "Chicken",
                        "Onion",
                        "Ketchup",
                        "Butter"));
                break;

            case "bibimbap":
                response.put("recipeName", "Bibimbap");
                response.put("country", "Korea");
                response.put("ingredients", Arrays.asList(
                        "Rice",
                        "Carrot",
                        "Meat",
                        "Egg",
                        "Cucumber",
                        "Spinach",
                        "Bean Sprouts",
                        "Radish",
                        "Gochujang",
                        "Sesame Oil"));
                break;

            case "rendang":
                response.put("recipeName", "Rendang");
                response.put("country", "Indonesia");
                response.put("ingredients", Arrays.asList(
                        "Meat",
                        "Galangal",
                        "Ginger",
                        "Turmeric",
                        "Shallots",
                        "Garlic",
                        "Chillies",
                        "Lime Leaves",
                        "Turmeric Leaves",
                        "Lemongrass",
                        "Coconut Milk",
                        "Black Pepper",
                        "Coriander"));
                break;

            default:
                response.put("success", false);
                response.put("message", "Recipe not found");
                return response;
        }

        response.put("success", true);
        return response;
    }
}