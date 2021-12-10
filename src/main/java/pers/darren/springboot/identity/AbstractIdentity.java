package pers.darren.springboot.identity;

import org.springframework.beans.factory.annotation.Autowired;

import pers.darren.springboot.example.model.Product;
import pers.darren.springboot.example.service.IExampleService;

public abstract class AbstractIdentity implements IIdentity {
    @Autowired
    private IExampleService exampleService;

    @Override
    public Product identity() {
        this.exampleService.printProperties();
        return null;
    }
}