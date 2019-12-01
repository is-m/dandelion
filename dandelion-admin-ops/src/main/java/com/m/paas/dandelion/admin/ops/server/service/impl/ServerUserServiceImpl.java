package com.m.paas.dandelion.admin.ops.server.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.m.paas.dandelion.admin.ops.server.mapper.ServerUserMapper;
import com.m.paas.dandelion.admin.ops.server.domain.ServerUser;
import com.m.paas.dandelion.admin.ops.server.service.IServerUserService;
import com.ruoyi.common.core.text.Convert;

/**
 * 服务器用户Service业务层处理
 * 
 * @author admin
 * @date 2019-11-14
 */
@Service
public class ServerUserServiceImpl implements IServerUserService 
{
    @Autowired
    private ServerUserMapper serverUserMapper;

    /**
     * 查询服务器用户
     * 
     * @param id 服务器用户ID
     * @return 服务器用户
     */
    @Override
    public ServerUser selectServerUserById(Long id)
    {
        return serverUserMapper.selectServerUserById(id);
    }

    /**
     * 查询服务器用户列表
     * 
     * @param serverUser 服务器用户
     * @return 服务器用户
     */
    @Override
    public List<ServerUser> selectServerUserList(ServerUser serverUser)
    {
        return serverUserMapper.selectServerUserList(serverUser);
    }

    /**
     * 新增服务器用户
     * 
     * @param serverUser 服务器用户
     * @return 结果
     */
    @Override
    public int insertServerUser(ServerUser serverUser)
    {
        serverUser.setCreateTime(DateUtils.getNowDate());
        return serverUserMapper.insertServerUser(serverUser);
    }

    /**
     * 修改服务器用户
     * 
     * @param serverUser 服务器用户
     * @return 结果
     */
    @Override
    public int updateServerUser(ServerUser serverUser)
    {
        serverUser.setUpdateTime(DateUtils.getNowDate());
        return serverUserMapper.updateServerUser(serverUser);
    }

    /**
     * 删除服务器用户对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteServerUserByIds(String ids)
    {
        return serverUserMapper.deleteServerUserByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除服务器用户信息
     * 
     * @param id 服务器用户ID
     * @return 结果
     */
    @Override
    public int deleteServerUserById(Long id)
    {
        return serverUserMapper.deleteServerUserById(id);
    }
}
