package com.project.ChocoWorld.web;
import com.project.ChocoWorld.repositories.OrderRepository;
import com.project.ChocoWorld.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/my_orders")
@SessionAttributes("user")
public class AccountController {
  private OrderRepository orderRepository;
    private UserRepository userRepository;


    public AccountController(OrderRepository orderRepository, UserRepository userRepository){
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    @ModelAttribute
    public void addOrdersToModel(Model model){
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user",userRepository.findByUsername(principal.getUsername()));
        model.addAttribute("orders",orderRepository.findAllByUser(userRepository.findByUsername(principal.getUsername())));
    }

    @GetMapping
    public String showOrderList(){
        return "my_orders";
    }
}
