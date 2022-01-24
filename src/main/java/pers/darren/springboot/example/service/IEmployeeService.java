package pers.darren.springboot.example.service;

import com.github.pagehelper.PageInfo;

import pers.darren.springboot.example.model.Employee;

public interface IEmployeeService {

    void add(Employee employee);

    Employee getById(Integer id);

    void modifyById(Employee employee);

    void modifySelectiveById(Employee employee);

    void removeById(Integer id);

    PageInfo<Employee> listPagination(Integer pageNum, Integer pageSize);
}