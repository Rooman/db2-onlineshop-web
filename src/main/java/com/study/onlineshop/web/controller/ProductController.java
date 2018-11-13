package com.study.onlineshop.web.controller;

import com.study.onlineshop.entity.Product;
import com.study.onlineshop.service.ProductService;
import com.study.onlineshop.web.templater.PageGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    PageGenerator pageGenerator = PageGenerator.instance();

    @RequestMapping(path = "/products", method = RequestMethod.GET)
    public void getAll(HttpServletResponse response) throws IOException {
        List<Product> products = productService.getAll();

        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("products", products);

        String page = pageGenerator.getPage("products", parameters);
        response.getWriter().write(page);
    }

    @RequestMapping(path = "/product/{id}", method = RequestMethod.GET)
    public String getByIdPath(HttpServletResponse response, @PathVariable int id) throws IOException {
        List<Product> products = productService.getAll();
        List<Product> filteredProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.getId() == id) {
                filteredProducts.add(product);
                break;
            }
        }

        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("products", filteredProducts);

        String page = pageGenerator.getPage("products", parameters);
        response.getWriter().write(page);
        return "redirect:/login";
    }

    @RequestMapping(path = "/product", method = RequestMethod.GET)
    @ResponseBody
    public String getByIdRequest(@RequestParam("id") int id) {
        List<Product> products = Arrays.asList(productService.getById(id));

        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("products", products);

        return pageGenerator.getPage("products", parameters);
    }
}
