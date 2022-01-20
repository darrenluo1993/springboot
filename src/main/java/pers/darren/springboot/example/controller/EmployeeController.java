package pers.darren.springboot.example.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

import pers.darren.springboot.example.model.Employee;
import pers.darren.springboot.example.service.IEmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @PostMapping("/add")
    public String add(@RequestBody final Employee employee) {
        employee.setCreatedBy(1);
        employee.setCreatedTime(new Date());
        this.employeeService.add(employee);

        return "success";
    }

    @GetMapping("/getById/{id}")
    public Employee getById(@PathVariable final Integer id) {
        return this.employeeService.getById(id);
    }

    @PostMapping("/modifyById")
    public String modifyById(@RequestBody final Employee employee) {
        employee.setModifiedBy(1);
        employee.setModifiedTime(new Date());
        this.employeeService.modifyById(employee);

        return "success";
    }

    @GetMapping("/removeById")
    public String removeById(final Integer id) {
        this.employeeService.removeById(id);
        return "success";
    }

    @GetMapping("/listPagination")
    public PageInfo<Employee> listPagination(final Integer pageNum, final Integer pageSize) {
        return this.employeeService.listPagination(pageNum, pageSize);
    }
}