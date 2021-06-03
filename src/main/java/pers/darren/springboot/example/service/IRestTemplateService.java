package pers.darren.springboot.example.service;

import pers.darren.springboot.example.model.Product;

/**
 * 使用Spring RestTemplate调用REST Services
 *
 * @CreatedBy Darren Luo
 * @CreatedTime Jun 3, 2021 3:32:12 PM
 */
public interface IRestTemplateService {
    /**
     * 调用ExampleController下的getProduct接口
     *
     * @CreatedBy Darren Luo
     * @CreatedTime Jun 3, 2021 3:17:57 PM
     * @return
     */
    Product callExampleGetProduct();
}