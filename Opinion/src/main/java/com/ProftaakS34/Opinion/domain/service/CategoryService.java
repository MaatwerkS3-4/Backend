package com.ProftaakS34.Opinion.domain.service;

import com.ProftaakS34.Opinion.domain.model.Category;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CategoryService {
    public List<String> getAvailableCategories(){
        return Stream.of(Category.values())
                .map(Category::name)
                .collect(Collectors.toList());
    }

    public Map<Category, Category> getReverseRecommendations(){
        Map<Category, Category> reverse = new HashMap<>();

        reverse.put(Category.BUSINESS, Category.SPORT);
        reverse.put(Category.EDUCATION, Category.POLITICS);
        reverse.put(Category.ENTERTAINMENT, Category.BUSINESS);
        reverse.put(Category.FOOD, Category.LITERATURE);
        reverse.put(Category.HEALTH, Category.HISTORY);
        reverse.put(Category.HISTORY, Category.ENTERTAINMENT);
        reverse.put(Category.LITERATURE, Category.HEALTH);
        reverse.put(Category.PHILOSOPHY, Category.TECHNOLOGY);
        reverse.put(Category.PSYCHOLOGY, Category.SCIENCE);
        reverse.put(Category.SCIENCE, Category.PSYCHOLOGY);
        reverse.put(Category.TECHNOLOGY, Category.PHILOSOPHY);
        reverse.put(Category.SPORT, Category.EDUCATION);
        reverse.put(Category.POLITICS, Category.PHILOSOPHY);

        return reverse;
    }
}
