package com.ProftaakS34.Opinion;

import com.ProftaakS34.Opinion.domain.model.Category;
import com.ProftaakS34.Opinion.domain.service.CategoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

class CategoryServiceTests {
    private CategoryService categoryService;

    private CategoryServiceTests() {
        categoryService = new CategoryService();
    }

    @Test
    void GetAvailableTest() {
        List<String> pulledCats = categoryService.getAvailableCategories();

        Assertions.assertEquals(13, pulledCats.size());
        Assertions.assertEquals("TECHNOLOGY", pulledCats.get(0));
        Assertions.assertEquals("FOOD", pulledCats.get(6));
    }

    @Test
    void GetReversesTest() {
        Map<Category, Category> pulledReverses = categoryService.getReverseRecommendations();

        Assertions.assertEquals(13, pulledReverses.size());
        Assertions.assertEquals(Category.SPORT, pulledReverses.get(Category.BUSINESS));
        Assertions.assertNotEquals(Category.SPORT, pulledReverses.get(Category.PHILOSOPHY));
    }
}
