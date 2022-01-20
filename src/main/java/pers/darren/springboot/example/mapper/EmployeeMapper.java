package pers.darren.springboot.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import pers.darren.springboot.example.model.Employee;

@Mapper
public interface EmployeeMapper {
    @Insert("""
            insert into employee
               (name,
                department,
                post,
                salary,
                createdBy,
                createdTime)
            values
               (#{name},
                #{department},
                #{post},
                #{salary},
                #{createdBy},
                #{createdTime})
            """)
    void add(Employee employee);

    @Update("""
            update
                employee
            set
                name = #{name},
                department = #{department},
                post = #{post},
                salary = #{salary},
                modifiedBy = #{modifiedBy},
                modifiedTime = #{modifiedTime}
            where
                id = #{id}
            """)
    void modifyById(Employee employee);

    @Select("""
            select
                id,
                name,
                department,
                post,
                salary,
                createdBy,
                createdTime,
                modifiedBy,
                modifiedTime
            from
                employee
            where
                id = #{id}
            """)
    Employee getById(Integer id);

    @Delete("delete from employee where id #{id}")
    void removeById(Integer id);

    @Select("""
            select
                id,
                name,
                department,
                post,
                salary,
                createdBy,
                createdTime,
                modifiedBy,
                modifiedTime
            from
                employee
            order by
                id
            """)
    List<Employee> listAll();
}