package com.m.paas.dandelion.admin.ops.server.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 服务器对象 mops_server
 * 
 * @author admin
 * @date 2019-11-14
 */
public class Server extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 主机名 */
    @Excel(name = "主机名")
    private String name;

    /** 域名/IP */
    @Excel(name = "域名/IP")
    private String host;

    /** SSH端口 */
    @Excel(name = "SSH端口")
    private Integer port;

    /** 是否删除 */
    private Long deleted;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setHost(String host) 
    {
        this.host = host;
    }

    public String getHost() 
    {
        return host;
    }
    public void setPort(Integer port) 
    {
        this.port = port;
    }

    public Integer getPort() 
    {
        return port;
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
            .append("name", getName())
            .append("host", getHost())
            .append("port", getPort())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("deleted", getDeleted())
            .toString();
    }
}
