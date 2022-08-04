package pers.darren.springboot.example.service;

import pers.darren.springboot.example.model.Customer;
import pers.darren.springboot.mybatis.EnDeCrypt;

/**
 * @author darren
 * @description 针对表【customer(客户表)】的数据库操作Service
 * @createDate 2022-08-02 10:20:48
 */
public interface CustomerService {

    void add(Customer customer);

    Customer getById(Integer id);

    Customer getByMobilePhone(EnDeCrypt mobilePhone);
}
