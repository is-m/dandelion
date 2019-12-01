package com.m.paas.dandelion.admin.ops.server.mapper;

import com.m.paas.dandelion.admin.ops.server.domain.ServerUser;
import java.util.List;

/**
 * 服务器用户Mapper接口
 * 
 * @author admin
 * @date 2019-11-14
 */
public interface ServerUserMapper 
{
    /**
     * 查询服务器用户
     * 
     * @param id 服务器用户ID
     * @return 服务器用户
     */
    public ServerUser selectServerUserById(Long id);

    /**
     * 查询服务器用户列表
     * 
     * @param serverUser 服务器用户
     * @return 服务器用户集合
     */
    public List<ServerUser> selectServerUserList(ServerUser serverUser);

    /**
     * 新增服务器用户
     * 
     * @param serverUser 服务器用户
     * @return 结果
     */
    public int insertServerUser(ServerUser serverUser);

    /**
     * 修改服务器用户
     * 
     * @param serverUser 服务器用户
     * @return 结果
     */
    public int updateServerUser(ServerUser serverUser);

    /**
     * 删除服务器用户
     * 
     * @param id 服务器用户ID
     * @return 结果
     */
    public int deleteServerUserById(Long id);

    /**
     * 批量删除服务器用户
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteServerUserByIds(String[] ids);
}
