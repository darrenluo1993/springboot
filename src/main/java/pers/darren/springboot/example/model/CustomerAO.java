package pers.darren.springboot.example.model;

import jakarta.validation.constraints.Size;

import java.io.Serial;
import java.io.Serializable;

/**
 * 客户AO（Argument Object）
 */
public class CustomerAO implements Serializable {
    @Serial
    private static final long serialVersionUID = -4717920493615299878L;
    /**
     * ID
     */
    private Integer id;
    /**
     * 姓名
     */
    @Size(min = 2, max = 50, message = "姓名长度必须在2至50个字符之间！")
    private String fullName;
    /**
     * 手机号码
     */
    @Size(min = 11, max = 11, message = "手机号码必须是11位数字！")
    private String mobilePhone;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }
}