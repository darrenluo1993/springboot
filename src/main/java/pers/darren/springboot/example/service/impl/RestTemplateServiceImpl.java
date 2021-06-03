package pers.darren.springboot.example.service.impl;

import static java.net.URI.create;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import pers.darren.springboot.example.model.Product;
import pers.darren.springboot.example.service.IRestTemplateService;

@Service
public class RestTemplateServiceImpl implements IRestTemplateService {

    private final RestTemplate restTemplate;

    /**
     * 注入RestTemplateBuilder以构建RestTemplate
     *
     * @CreatedBy Darren Luo
     * @CreatedTime Jun 3, 2021 3:34:51 PM
     * @param builder {@link RestTemplateBuilder}
     */
    public RestTemplateServiceImpl(final RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @Override
    public Product callExampleGetProduct() {
        return this.restTemplate.getForObject(create("http://localhost:7777/example/getProduct"), Product.class);
    }
}