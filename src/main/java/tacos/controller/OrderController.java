package tacos.controller;


import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.support.SessionStatus;

import tacos.model.TacoOrder;
import tacos.repository.jdbc_template.OrderRepository;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {

    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @ModelAttribute(name="tacoOrder")
    public TacoOrder order() {
        log.info("adding tacoOrder object to Model Attribute ...");
        return new TacoOrder();
    }

    @GetMapping("/current")
    public String orderForm (Model model) {
        log.info("calling order Form view...");
        System.out.println(model.getAttribute("tacoOrder"));
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid TacoOrder order, Errors errors, SessionStatus sessionStatus) {
        log.info("Order Submitted : {} ", order);
        if (errors.hasErrors())
            return "orderForm";
        orderRepository.save(order);
        sessionStatus.setComplete();
        return "redirect:/";
    }
}
