package com.m.paas.dandelion.ops.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 服务器用户对象 mops_host_user
 * 
 * @author admin
 * @date 2019-12-10
 */
public class HostUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 服务器ID */
    @Excel(name = "服务器ID")
    private Long mopsHostId;

    /** 账号 */
    @Excel(name = "账号")
    private String account;

    /** 密码 */
    @Excel(name = "密码")
    private String password;

    /** 是否被删除 */
    @Excel(name = "是否被删除")
    private Long deleted;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setMopsHostId(Long mopsHostId) 
    {
        this.mopsHostId = mopsHostId;
    }

    public Long getMopsHostId() 
    {
        return mopsHostId;
    }
    public void setAccount(String account) 
    {
        this.account = account;
    }

    public String getAccount() 
    {
        return account;
    }
    public void setPassword(String password) 
    {
        this.password = password;
    }

    public String getPassword() 
    {
        return password;
    }
    public void setDeleted(Long deleted) 
    {
        this.deleted = deleted;
    }

    public Long getDeleted() 
    {
        return deleted;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("mopsHostId", getMopsHostId())
            .append("account", getAccount())
            .append("password", getPassword())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("deleted", getDeleted())
            .toString();
    }
}
