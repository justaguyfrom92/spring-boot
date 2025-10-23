package com.example.spring_boot.controller;

import com.example.spring_boot.model.Product;
import com.example.spring_boot.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {
    
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping({"", "/"})
    public String index(@RequestParam(required = false) String category, Model model) {
        List<Product> products;
        List<String> categories = productService.getAllCategories();
        
        if (category != null && !category.isEmpty()) {
            products = productService.getProductsByCategory(category);
            model.addAttribute("selectedCategory", category);
        } else {
            products = productService.getAllProducts();
            model.addAttribute("selectedCategory", "All");
        }
        
        model.addAttribute("products", products);
        model.addAttribute("categories", categories);
        
        return "products/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Long id, Model model)
    {
        return productService.getProductById(id)
                .map(product -> {
                    model.addAttribute("product", product);
                    return "show";
                })
                .orElse("redirect:/products");
    }

    @GetMapping("/new")
    public String newProduct(Model model)
    {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", productService.getAllCategories());
        return "products/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Product product, RedirectAttributes redirectAttributes)
    {
        productService.addProduct(product);
        redirectAttributes.addFlashAttribute("message", "Product created successfully!");
        return "redirect:/products";
    }

    @GetMapping("/{id}/edit")
public String edit(@PathVariable Long id, Model model)
{
    Optional<Product> productOptional = productService.getProductById(id);
    
    // Debug print - check if product exists first
    if (productOptional.isPresent()) {
        System.out.println("Editing product: " + productOptional.get());
    } else {
        System.out.println("Product with ID " + id + " not found.");
    }

    return productOptional
            .map(product -> {
                model.addAttribute("product", product);
                model.addAttribute("categories", productService.getAllCategories());
                return "products/edit";
            })
            .orElse("redirect:/products");
}

    @PostMapping("/{id}")
    public String update(@PathVariable Long id, @ModelAttribute Product product, 
                        RedirectAttributes redirectAttributes) {
        product.setId(id);
        productService.updateProduct(id, product);
        redirectAttributes.addFlashAttribute("message", "Product updated successfully!");
        return "redirect:/products/" + id;
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        productService.deleteProduct(id);
        redirectAttributes.addFlashAttribute("message", "Product deleted successfully!");
        return "redirect:/products";
    }
}