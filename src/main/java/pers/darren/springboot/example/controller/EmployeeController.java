package pers.darren.springboot.example.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pers.darren.springboot.example.model.Employee;
import pers.darren.springboot.example.model.EmployeeAO;
import pers.darren.springboot.example.service.IEmployeeService;

import java.util.Date;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @PostMapping("/add")
    public String add(@RequestBody @Validated final Employee employee) {
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
    public String modifyById(@RequestBody @Validated final Employee employee) {
        employee.setModifiedBy(1);
        employee.setModifiedTime(new Date());
        this.employeeService.modifyById(employee);

        return "success";
    }

    @PostMapping("/modifySelectiveById")
    public String modifySelectiveById(@RequestBody final Employee employee) {
        employee.setModifiedBy(1);
        employee.setModifiedTime(new Date());
        this.employeeService.modifySelectiveById(employee);

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

    @PostMapping("/transactionControlCase1")
    public String transactionControlCase1(@RequestBody @Validated final EmployeeAO employeeAO) {
        this.employeeService.transactionControlCase1(employeeAO);
        return "success";
    }

    @PostMapping("/transactionControlCase2")
    public String transactionControlCase2(@RequestBody @Validated final EmployeeAO employeeAO) {
        this.employeeService.transactionControlCase2(employeeAO);
        return "success";
    }

    @PostMapping("/transactionControlCase3")
    public String transactionControlCase3(@RequestBody @Validated final EmployeeAO employeeAO) {
        this.employeeService.transactionControlCase3(employeeAO);
        return "success";
    }
}