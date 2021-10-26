package net.codejava.productController;

import net.codejava.Entity.Product;
import net.codejava.productService.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

    @Controller
    public class ProductController {
    @Autowired
    private ProductService productService;


    @RequestMapping("/") // homepage
    public String viewHomePage(Model model){
        List<Product> listProducts = productService.listAll();
        model.addAttribute("listproducts",listProducts);
        return "index";
    }

    @RequestMapping("/new")
    public String showNewProductForm(Model model){
        Product product = new Product();
        model.addAttribute("product",product);
        return "new_product";

    }
//In Spring MVC, the model works a container that contains the data of the application.
// It is required to place the Model interface in the controller part of the application. ...
// The object of HttpServletRequest reads the information provided by the user and pass it to the Model interface.
    @RequestMapping(value="/save", method = RequestMethod.POST)
    public String saveProduct(@ModelAttribute("product")Product product){
        productService.save(product);
        return "redirect:/"; // "/" this means redirect to home page
    }

//    ModelAndView is a holder for both Model and View in the web MVC framework.
//    These two classes are distinct; ModelAndView merely holds both to make it possible for a
//    controller to return both model and view in a single return value.
    @RequestMapping("/edit/{id}")
    public ModelAndView showEditProductForm(@PathVariable(name = "id") Long id){
        ModelAndView mav = new ModelAndView("edit_product");
        Product product =productService.get(id);
        mav.addObject("product",product);
        return mav;
    }

    @RequestMapping("/delete/{id}")
    public String deleteProduct(@PathVariable(name="id") long id){
        productService.delete(id);
        return "redirect:/";
    }



}
