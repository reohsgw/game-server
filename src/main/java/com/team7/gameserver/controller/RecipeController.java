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
                response.put("steps", Arrays.asList(
                        "Melt butter in a pan.",
                        "Cook onion and chicken until they are soft.",
                        "Add rice and ketchup, then stir well.",
                        "Cook the egg in another pan.",
                        "Place the fried rice inside the egg.",
                        "Fold the egg to make omurice."));
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
                response.put("steps", Arrays.asList(
                        "Cook the rice with water.",
                        "Cut carrot, cucumber, and radish into small pieces.",
                        "Cook meat, spinach, bean sprouts, and egg.",
                        "Put cooked rice into a bowl.",
                        "Add vegetables, meat, and egg on top of the rice.",
                        "Add gochujang and sesame oil.",
                        "Mix everything well before eating."));
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
                response.put("steps", Arrays.asList(
                        "Blend galangal, ginger, turmeric, shallots, garlic, chillies, and coriander.",
                        "Cook the blended spices with lime leaves, turmeric leaves, and lemongrass.",
                        "Add meat and stir it with the spice base.",
                        "Add coconut milk and black pepper.",
                        "Slow cook until the sauce becomes thick.",
                        "Serve the rendang when the meat is tender."));
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