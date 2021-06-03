package pers.darren.springboot.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pers.darren.springboot.example.model.Product;
import pers.darren.springboot.example.service.IRestTemplateService;

@RestController
@RequestMapping("/restTemplate")
public class RestTemplateController {

    @Autowired
    private IRestTemplateService restTemplateService;

    @PostMapping("/getProduct")
    public Product getProduct() {
        return this.restTemplateService.callExampleGetProduct();
    }
}