package com.granovskiy;

import com.granovskiy.controller.LoginUserController;

import com.granovskiy.controller.GetAllCategoriesController;
import com.granovskiy.controller.GetCategoryByIdController;
import com.granovskiy.controller.LoginUserController;
import com.granovskiy.service.CategoryService;
import com.granovskiy.service.CategoryServiceImpl;
import com.granovskiy.service.UserService;
import com.granovskiy.service.UserServiceImpl;

public class Factory {

    public static LoginUserController getLoginUserController(UserService userService) {
        return new LoginUserController(userService);
    }

    public static UserService getUserServiceImpl() {
        return new UserServiceImpl();
    }

    public static GetAllCategoriesController getGetAllCategoriesController(CategoryService categoryService) {
        return new GetAllCategoriesController(categoryService);
    }

    public static CategoryService getCategoryService() {
        return new CategoryServiceImpl();
    }

    public static GetCategoryByIdController getGetCategoryByIdController(CategoryService categoryService) {
        return new GetCategoryByIdController(categoryService);
    }
}
