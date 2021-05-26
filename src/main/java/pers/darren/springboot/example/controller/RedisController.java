package pers.darren.springboot.example.controller;

import static java.util.concurrent.TimeUnit.SECONDS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @PostMapping("/setStringKey")
    public void setStringKey(final String key, final String value) {
        this.redisTemplate.opsForValue().set(key, value, 30, SECONDS);
    }

    @PostMapping("/getStringKey")
    public String getStringKey(final String key) {
        return this.redisTemplate.opsForValue().get(key);
    }
}