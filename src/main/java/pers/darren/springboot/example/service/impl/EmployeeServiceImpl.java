package pers.darren.springboot.example.service.impl;

import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;
import pers.darren.springboot.example.mapper.EmployeeMapper;
import pers.darren.springboot.example.model.Employee;
import pers.darren.springboot.example.model.EmployeeAO;
import pers.darren.springboot.example.service.IEmployeeService;

import java.math.BigDecimal;
import java.util.Date;

import static com.github.pagehelper.page.PageMethod.startPage;
import static org.springframework.aop.framework.AopContext.currentProxy;
import static org.springframework.beans.BeanUtils.copyProperties;

@Slf4j
@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private TransactionTemplate transactionTemplate;

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
    public void modifySelectiveById(final Employee employee) {
        this.employeeMapper.modifySelectiveById(employee);
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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void transactionControlCase1(EmployeeAO employeeAO) {
        // 根据员工id查询员工信息并打印
        Employee employee = this.employeeMapper.getById(employeeAO.getId());
        log.info("Employee info in database table>>>" + employee.toString());
        // 新增员工信息
        employee = new Employee();
        copyProperties(employeeAO, employee, "id", "modifiedBy", "modifiedTime");
        employee.setCreatedBy(1);
        employee.setCreatedTime(new Date());
        this.employeeMapper.add(employee);
        // 查出上述新增的员工信息
        employee = this.employeeMapper.getEmployeeViaDynamicParams(employeeAO);
        // 修改上述新增的员工信息
        employee.setName("事务控制案例1");
        employee.setDepartment("事务1部");
        employee.setPost("事务控制经理1");
        employee.setSalary(new BigDecimal(11000));
        employee.setCreatedBy(null);
        employee.setCreatedTime(null);
        employee.setModifiedBy(1);
        employee.setModifiedTime(new Date());
        this.employeeMapper.modifyById(employee);
    }

    @Override
    public void transactionControlCase2(EmployeeAO employeeAO) {
        // 根据员工id查询员工信息并打印
        Employee result = this.employeeMapper.getById(employeeAO.getId());
        log.info("Employee info in database table>>>" + result.toString());
        this.transactionTemplate.execute(status -> {
            // 新增员工信息
            Employee employee = new Employee();
            copyProperties(employeeAO, employee, "id", "modifiedBy", "modifiedTime");
            employee.setCreatedBy(2);
            employee.setCreatedTime(new Date());
            this.employeeMapper.add(employee);
            // 查出上述新增的员工信息
            employee = this.employeeMapper.getEmployeeViaDynamicParams(employeeAO);
            // 修改上述新增的员工信息
            employee.setName("事务控制案例2");
            employee.setDepartment("事务2部");
            employee.setPost("事务控制经理2");
            employee.setSalary(new BigDecimal(12000));
            employee.setCreatedBy(null);
            employee.setCreatedTime(null);
            employee.setModifiedBy(2);
            employee.setModifiedTime(new Date());
            this.employeeMapper.modifyById(employee);

            return null;
        });
    }

    @Override
    public void transactionControlCase3(EmployeeAO employeeAO) {
        // 根据员工id查询员工信息并打印
        Employee employee = this.employeeMapper.getById(employeeAO.getId());
        log.info("Employee info in database table>>>" + employee.toString());
        ((EmployeeServiceImpl) currentProxy()).addAndModifyEmployee(employeeAO);
    }

    @Transactional(rollbackFor = Exception.class)
    public void addAndModifyEmployee(EmployeeAO employeeAO) {
        // 新增员工信息
        Employee employee = new Employee();
        copyProperties(employeeAO, employee, "id", "modifiedBy", "modifiedTime");
        employee.setCreatedBy(3);
        employee.setCreatedTime(new Date());
        this.employeeMapper.add(employee);
        // 查出上述新增的员工信息
        employee = this.employeeMapper.getEmployeeViaDynamicParams(employeeAO);
        // 修改上述新增的员工信息
        employee.setName("事务控制案例3");
        employee.setDepartment("事务3部");
        employee.setPost("事务控制经理3");
        employee.setSalary(new BigDecimal(13000));
        employee.setCreatedBy(null);
        employee.setCreatedTime(null);
        employee.setModifiedBy(3);
        employee.setModifiedTime(new Date());
        this.employeeMapper.modifyById(employee);
    }
}