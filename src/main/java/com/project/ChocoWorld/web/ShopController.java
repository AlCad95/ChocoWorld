package com.project.ChocoWorld.web;
import com.project.ChocoWorld.entities.Order;
import com.project.ChocoWorld.entities.OrderItem;
import com.project.ChocoWorld.entities.Product;
import com.project.ChocoWorld.repositories.OrderRepository;
import com.project.ChocoWorld.repositories.ProductRepository;
import com.project.ChocoWorld.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/shop")
@SessionAttributes({"order","user"})
public class ShopController {
    private ProductRepository productRepository;
    private OrderRepository orderRepository;
    private UserRepository userRepository;

    public ShopController(ProductRepository productRepository, OrderRepository orderRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public String showOrderForm(Model model) {
        Order order = new Order();
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        order.setUser(userRepository.findByUsername(principal.getUsername()));

        List<Product> list = (List<Product>) productRepository.findAll();
        for (Product product : list) {
            order.getOrderDetail().getOrderItems().add(new OrderItem(order.getOrderDetail(), product, 0));
        }

        model.addAttribute("order", order);
        model.addAttribute("user", userRepository.findByUsername(principal.getUsername()));
        return "shop";
    }

    @PostMapping
    public String showCart(@ModelAttribute Order order) {
        order.getOrderDetail().getOrderItems().removeIf(p -> p.getQuantity() == 0);
        order.calculateSum();
        return "cart";
    }

    @GetMapping("/purchase")
    @Transactional
    public String purchase(@ModelAttribute Order order, Model model) {
        try {
            if (order.getSum() == 0) {
                throw new RuntimeException(" Error.Check your cart balance");
            }

            if (order.getSum() > order.getUser().getMoneyBalance()) {
                throw new RuntimeException("Error! Check your cart balance");
            }
        } catch (RuntimeException exception) {
            model.addAttribute("message", exception.getMessage());
            return "cart";
        }


        order.getOrderDetail().getOrderItems()
                .forEach(orderItem -> orderItem.getProduct()
                        .setInStock(orderItem.getProduct().getInStock() - orderItem.getQuantity()));


        order.getUser().setMoneyBalance(order.getUser().getMoneyBalance() -
                order.getSum());


        order.getOrderDetail().getOrderItems().forEach(orderItem -> productRepository.save(orderItem.getProduct()));
        orderRepository.save(order);
        userRepository.save(order.getUser());
        return "Thank you!!";
    }
   
}
