package com.ProftaakS34.Opinion.domain.service;

import com.ProftaakS34.Opinion.domain.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CategoryService {
    public List<String> getAvailableCategories(){
        return Stream.of(Category.values())
                .map(Category::name)
                .collect(Collectors.toList());
    }
}
