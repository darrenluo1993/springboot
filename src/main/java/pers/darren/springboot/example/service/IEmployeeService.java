package pers.darren.springboot.example.service;

import com.github.pagehelper.PageInfo;
import pers.darren.springboot.example.model.Employee;
import pers.darren.springboot.example.model.EmployeeAO;

public interface IEmployeeService {

    void add(Employee employee);

    Employee getById(Integer id);

    void modifyById(Employee employee);

    void modifySelectiveById(Employee employee);

    void removeById(Integer id);

    PageInfo<Employee> listPagination(Integer pageNum, Integer pageSize);

    void transactionControlCase1(EmployeeAO employeeAO);

    void transactionControlCase2(EmployeeAO employeeAO);

    void transactionControlCase3(EmployeeAO employeeAO);
}