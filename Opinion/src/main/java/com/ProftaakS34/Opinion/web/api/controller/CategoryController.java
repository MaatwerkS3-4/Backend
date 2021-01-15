package com.ProftaakS34.Opinion.web.api.controller;

import com.ProftaakS34.Opinion.domain.model.Category;
import com.ProftaakS34.Opinion.domain.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.KeyPair;
import java.util.*;

@RestController
@RequestMapping("/category")
@Api(tags = "Categories")
public class CategoryController {
    private final CategoryService categoryService;


    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * Get all available category values
     * @return A list of strings with the enum values

    */
    @ApiOperation(
            value = "Get all available category values"
    )
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Values have been returned")
    })
    @GetMapping
    public ResponseEntity<List<String>> getCategories(){
        List<String> resource = categoryService.getAvailableCategories();
        return ResponseEntity.ok(resource);
    }

    /**
     * Get reverse recommendations for every category
     * @return A list of sets with a category and a recommended reverse category
     */
    @ApiOperation(
            value = "Get a reverse recommendation for every category"
    )
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Reverse recommendations have been returned")
    })
    @GetMapping(path = "/reverse")
    public ResponseEntity<List<Pair<String, String>>> getReverseRecommendations(){
        Map<Category, Category> reverse = categoryService.getReverseRecommendations();
        List<Pair<String, String>> resource = new ArrayList<>();

        for(Map.Entry<Category, Category> entry : reverse.entrySet()){
            resource.add(Pair.of(entry.getKey().name(), entry.getValue().name()));
        }

        return ResponseEntity.ok(resource);
    }
}
