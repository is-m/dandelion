package com.m.paas.dandelion.ops.service;

import com.m.paas.dandelion.ops.domain.HostUser;
import java.util.List;

/**
 * 服务器用户Service接口
 * 
 * @author admin
 * @date 2019-12-10
 */
public interface IHostUserService 
{
    /**
     * 查询服务器用户
     * 
     * @param id 服务器用户ID
     * @return 服务器用户
     */
    public HostUser selectHostUserById(Long id);

    /**
     * 查询服务器用户列表
     * 
     * @param hostUser 服务器用户
     * @return 服务器用户集合
     */
    public List<HostUser> selectHostUserList(HostUser hostUser);

    /**
     * 新增服务器用户
     * 
     * @param hostUser 服务器用户
     * @return 结果
     */
    public int insertHostUser(HostUser hostUser);

    /**
     * 修改服务器用户
     * 
     * @param hostUser 服务器用户
     * @return 结果
     */
    public int updateHostUser(HostUser hostUser);

    /**
     * 批量删除服务器用户
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteHostUserByIds(String ids);

    /**
     * 删除服务器用户信息
     * 
     * @param id 服务器用户ID
     * @return 结果
     */
    public int deleteHostUserById(Long id);
}
