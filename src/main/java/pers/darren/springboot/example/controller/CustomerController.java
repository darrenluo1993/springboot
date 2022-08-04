package pers.darren.springboot.example.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pers.darren.springboot.example.model.Customer;
import pers.darren.springboot.example.model.CustomerAO;
import pers.darren.springboot.example.service.CustomerService;
import pers.darren.springboot.mybatis.EnDeCrypt;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/add")
    public String add(@RequestBody @Validated CustomerAO customerAO) {
        Customer customer = new Customer();
        customer.setFullName(customerAO.getFullName());
        customer.setMobilePhone(new EnDeCrypt(customerAO.getMobilePhone()));
        this.customerService.add(customer);

        return "success";
    }

    @GetMapping("/getById")
    public Customer getById(Integer id) {
        return this.customerService.getById(id);
    }

    @GetMapping("/getByMobilePhone")
    public Customer getByMobilePhone(String mobilePhone) {
        EnDeCrypt phone = new EnDeCrypt(mobilePhone);
        return this.customerService.getByMobilePhone(phone);
    }
}
