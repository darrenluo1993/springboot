package pers.darren.springboot.example.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pers.darren.springboot.example.model.Product;

import java.math.BigDecimal;

@Slf4j
@RestController
@RequestMapping("/example")
public class ExampleController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello, Spring Boot!";
    }

    @GetMapping("/aboutMe")
    public String aboutMe() {
        return "<a href='https://about.me/darrenluo' target='_blank'>About Darren Luo</a>";
    }

    @GetMapping("/getProduct")
    public Product getProduct() {
        final Product product = new Product();
        product.setName("Product Name");
        product.setSupplier("Supplier Name");
        product.setQuantity(10000);
        product.setPrice(new BigDecimal(1234));
        return product;
    }

    @PostMapping("/httpClientTest")
    public String httpClientTest(@RequestBody final String requestBody, final HttpServletRequest request) {
        final StringBuilder builder = new StringBuilder();
        builder.append("sign:").append(request.getHeader("sign")).append(",");
        builder.append("token:").append(request.getHeader("token")).append(",");
        builder.append("timestamp:").append(request.getHeader("timestamp")).append(",");
        builder.append("requestBody:").append(requestBody);

        final String responseBody = builder.toString();
        log.info("responseBody>>>" + responseBody);

        return responseBody;
    }
}