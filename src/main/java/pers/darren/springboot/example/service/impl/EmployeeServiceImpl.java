package pers.darren.springboot.example.service.impl;

import static com.github.pagehelper.page.PageMethod.startPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;

import pers.darren.springboot.example.mapper.EmployeeMapper;
import pers.darren.springboot.example.model.Employee;
import pers.darren.springboot.example.service.IEmployeeService;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public void add(final Employee employee) {
        this.employeeMapper.add(employee);
    }

    @Override
    public Employee getById(final Integer id) {
        return this.employeeMapper.getById(id);
    }

    @Override
    public void modifyById(final Employee employee) {
        this.employeeMapper.modifyById(employee);
    }

    @Override
    public void removeById(final Integer id) {
        this.employeeMapper.removeById(id);
    }

    @Override
    public PageInfo<Employee> listPagination(final Integer pageNum, final Integer pageSize) {
        startPage(pageNum, pageSize);
        return new PageInfo<>(this.employeeMapper.listAll());
    }
}