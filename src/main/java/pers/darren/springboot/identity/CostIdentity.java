package pers.darren.springboot.identity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pers.darren.springboot.example.model.Product;
import pers.darren.springboot.example.service.IMailSendService;
import pers.darren.springboot.example.service.IRestTemplateService;

@Component
public class CostIdentity extends AbstractIdentity {
    @Autowired
    private IMailSendService mailSendService;
    @Autowired
    private IRestTemplateService restTemplateService;

    @Override
    public Product identity() {
        super.identity();
        this.mailSendService.sendSimpleMailMessage("Cost", "Cost！");
        return this.restTemplateService.callExampleGetProduct();
    }
}