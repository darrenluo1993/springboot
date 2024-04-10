package pers.darren.springboot.example.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/redisSession")
public class RedisSessionController {

    @PostMapping("/setSessionAttr")
    public void setSessionAttr(final HttpSession session) {
        session.setAttribute("Name", "Darren Luo");
        session.setAttribute("Phone", "15111181234");
        session.setAttribute("IdNo", "432524199203044910");
    }

    @PostMapping("/getSessionAttr")
    public Map<String, String> getSessionAttr(final HttpSession session) {
        final Map<String, String> map = new HashMap<>();
        map.put("SessionId", session.getId());
        map.put("Name", (String) session.getAttribute("Name"));
        map.put("Phone", (String) session.getAttribute("Phone"));
        map.put("IdNo", (String) session.getAttribute("IdNo"));
        return map;
    }
}