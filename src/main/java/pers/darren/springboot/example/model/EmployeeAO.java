package pers.darren.springboot.example.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 员工信息AO（Argument Object）
 *
 * @CreatedBy Darren Luo
 * @CreatedTime 2022年2月18日 9时38分
 */
@Getter
@Setter
@ToString
public class EmployeeAO implements Serializable {
    @Serial
    private static final long serialVersionUID = -5504987724162716780L;
    /**
     * 员工ID
     */
    private Integer id;
    /**
     * 员工姓名
     */
    @NotBlank(message = "员工姓名不能为空！")
    private String name;
    /**
     * 所属部门
     */
    @NotBlank(message = "所属部门不能为空！")
    private String department;
    /**
     * 员工职务
     */
    @NotBlank(message = "员工职务不能为空！")
    private String post;
    /**
     * 员工薪资
     */
    @NotNull(message = "员工薪资不能为空！")
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