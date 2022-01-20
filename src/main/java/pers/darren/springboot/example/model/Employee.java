package pers.darren.springboot.example.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * 员工信息表
 *
 * @CreatedBy Darren Luo
 * @CreatedTime Jan 20, 2022 9:38:29 AM
 */
@Getter
@Setter
public class Employee implements Serializable {

    private static final long serialVersionUID = -7563860498643860143L;
    /**
     * 员工ID
     */
    private Integer id;
    /**
     * 员工姓名
     */
    private String name;
    /**
     * 所属部门
     */
    private String department;
    /**
     * 员工职务
     */
    private String post;
    /**
     * 员工薪资
     */
    private BigDecimal salary;
    /**
     * 创建人
     */
    private Integer createdBy;
    /**
     * 创建时间
     */
    private Date createdTime;
    /**
     * 修改人
     */
    private Integer modifiedBy;
    /**
     * 修改时间
     */
    private Date modifiedTime;
}