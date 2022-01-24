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
               (#{name,jdbcType=VARCHAR},
                #{department,jdbcType=VARCHAR},
                #{post,jdbcType=VARCHAR},
                #{salary,jdbcType=DECIMAL},
                #{createdBy,jdbcType=INTEGER},
                #{createdTime,jdbcType=TIMESTAMP})
            """)
    void add(Employee employee);

    @Update("""
            update
                employee
            set
                name = #{name,jdbcType=VARCHAR},
                department = #{department,jdbcType=VARCHAR},
                post = #{post,jdbcType=VARCHAR},
                salary = #{salary,jdbcType=DECIMAL},
                modifiedBy = #{modifiedBy,jdbcType=INTEGER},
                modifiedTime = #{modifiedTime,jdbcType=TIMESTAMP}
            where
                id = #{id,jdbcType=INTEGER}
            """)
    void modifyById(Employee employee);

    void modifySelectiveById(Employee employee);

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
                id = #{id,jdbcType=INTEGER}
            """)
    Employee getById(Integer id);

    @Delete("delete from employee where id #{id,jdbcType=INTEGER}")
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