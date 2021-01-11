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

        reverse.put(Category.Business, Category.Sport);
        reverse.put(Category.Education, Category.Politics);
        reverse.put(Category.Entertainment, Category.Business);
        reverse.put(Category.Food, Category.Literature);
        reverse.put(Category.Health, Category.History);
        reverse.put(Category.History, Category.Entertainment);
        reverse.put(Category.Literature, Category.Health);
        reverse.put(Category.Philosophy, Category.Technology);
        reverse.put(Category.Psychology, Category.Science);
        reverse.put(Category.Science, Category.Psychology);
        reverse.put(Category.Technology, Category.Philosophy);
        reverse.put(Category.Sport, Category.Education);
        reverse.put(Category.Politics, Category.Philosophy);

        return reverse;
    }
}
