package pers.darren.springboot.example.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import pers.darren.springboot.example.model.Customer;
import pers.darren.springboot.mybatis.EnDeCrypt;

/**
 * @author darren
 * @description 针对表【customer(客户表)】的数据库操作Mapper
 * @createDate 2022-08-02 10:20:48
 * @Entity pers.darren.springboot.example.model.Customer
 */
@Mapper
public interface CustomerMapper {
    @Insert("""
            insert into customer
               (fullName,
                mobilePhone)
            values
               (#{fullName,jdbcType=VARCHAR},
                #{mobilePhone,jdbcType=VARCHAR})
            """)
    int add(Customer customer);

    @Select("""
            select
                id,
                fullName,
                mobilePhone
            from
                customer
            where
                id = #{id,jdbcType=INTEGER}
            """)
    Customer getById(Integer id);

    @Select("""
            select
                id,
                fullName,
                mobilePhone
            from
                customer
            where
                mobilePhone = #{mobilePhone,jdbcType=VARCHAR}
            """)
    Customer getByMobilePhone(EnDeCrypt mobilePhone);
}
