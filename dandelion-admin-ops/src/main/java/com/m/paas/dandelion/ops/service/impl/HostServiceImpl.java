package com.m.paas.dandelion.ops.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.m.paas.dandelion.ops.mapper.HostMapper;
import com.m.paas.dandelion.ops.domain.Host;
import com.m.paas.dandelion.ops.service.IHostService;
import com.ruoyi.common.core.text.Convert;

/**
 * 服务器Service业务层处理
 * 
 * @author admin
 * @date 2019-12-10
 */
@Service
public class HostServiceImpl implements IHostService 
{
    @Autowired
    private HostMapper hostMapper;

    /**
     * 查询服务器
     * 
     * @param id 服务器ID
     * @return 服务器
     */
    @Override
    public Host selectHostById(Long id)
    {
        return hostMapper.selectHostById(id);
    }

    /**
     * 查询服务器列表
     * 
     * @param host 服务器
     * @return 服务器
     */
    @Override
    public List<Host> selectHostList(Host host)
    {
        return hostMapper.selectHostList(host);
    }

    /**
     * 新增服务器
     * 
     * @param host 服务器
     * @return 结果
     */
    @Override
    public int insertHost(Host host)
    {
        host.setCreateTime(DateUtils.getNowDate());
        return hostMapper.insertHost(host);
    }

    /**
     * 修改服务器
     * 
     * @param host 服务器
     * @return 结果
     */
    @Override
    public int updateHost(Host host)
    {
        host.setUpdateTime(DateUtils.getNowDate());
        return hostMapper.updateHost(host);
    }

    /**
     * 删除服务器对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteHostByIds(String ids)
    {
        return hostMapper.deleteHostByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除服务器信息
     * 
     * @param id 服务器ID
     * @return 结果
     */
    @Override
    public int deleteHostById(Long id)
    {
        return hostMapper.deleteHostById(id);
    }
}
