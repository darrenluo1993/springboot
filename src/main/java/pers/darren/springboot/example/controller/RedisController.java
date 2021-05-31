package pers.darren.springboot.example.controller;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @PostMapping("/rpushListKey")
    public void rpushListKey(final String key, @RequestParam("list") final List<String> list) {
        this.redisTemplate.delete(key);
        this.redisTemplate.opsForList().rightPushAll(key, list);
    }

    @PostMapping("/rangeListKey")
    public List<String> rangeListKey(final String key) {
        final ListOperations<String, String> operations = this.redisTemplate.opsForList();
        return operations.range(key, 0, operations.size(key));
    }
}