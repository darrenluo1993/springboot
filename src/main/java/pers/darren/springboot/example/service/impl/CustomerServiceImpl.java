package pers.darren.springboot.example.service.impl;

import org.springframework.stereotype.Service;
import pers.darren.springboot.example.mapper.CustomerMapper;
import pers.darren.springboot.example.model.Customer;
import pers.darren.springboot.example.service.CustomerService;
import pers.darren.springboot.mybatis.EnDeCrypt;

/**
 * @author darren
 * @description 针对表【customer(客户表)】的数据库操作Service实现
 * @createDate 2022-08-02 10:20:48
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }

    @Override
    public void add(Customer customer) {
        this.customerMapper.add(customer);
    }

    @Override
    public Customer getById(Integer id) {
        return this.customerMapper.getById(id);
    }

    @Override
    public Customer getByMobilePhone(EnDeCrypt mobilePhone) {
        return this.customerMapper.getByMobilePhone(mobilePhone);
    }
}
