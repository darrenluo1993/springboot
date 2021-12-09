package pers.darren.springboot.identity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pers.darren.springboot.example.model.Product;
import pers.darren.springboot.example.service.IExampleService;
import pers.darren.springboot.example.service.IMailSendService;
import pers.darren.springboot.example.service.IRestTemplateService;

@Component
public class CutlineIdentity implements IIdentity {
    @Autowired
    private IExampleService exampleService;
    @Autowired
    private IMailSendService mailSendService;
    @Autowired
    private IRestTemplateService restTemplateService;

    @Override
    public Product identity() {
        this.exampleService.printProperties();
        this.mailSendService.sendSimpleMailMessage("Cutline", "Cutline！");
        return this.restTemplateService.callExampleGetProduct();
    }
}