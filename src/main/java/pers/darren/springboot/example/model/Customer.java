package pers.darren.springboot.example.model;

import pers.darren.springboot.mybatis.EnDeCrypt;

import java.io.Serializable;

/**
 * 客户表
 *
 * @TableName customer
 */
public class Customer implements Serializable {
    /**
     * ID
     */
    private Integer id;
    /**
     * 姓名
     */
    private String fullName;
    /**
     * 手机号码
     */
    private EnDeCrypt mobilePhone;

    private static final long serialVersionUID = 1L;

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

    public EnDeCrypt getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(EnDeCrypt mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Customer other = (Customer) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId())) && (this.getFullName() == null ? other.getFullName() == null : this.getFullName().equals(other.getFullName())) && (this.getMobilePhone() == null ? other.getMobilePhone() == null : this.getMobilePhone().equals(other.getMobilePhone()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getFullName() == null) ? 0 : getFullName().hashCode());
        result = prime * result + ((getMobilePhone() == null) ? 0 : getMobilePhone().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", fullname=").append(fullName);
        sb.append(", mobilephone=").append(mobilePhone);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}