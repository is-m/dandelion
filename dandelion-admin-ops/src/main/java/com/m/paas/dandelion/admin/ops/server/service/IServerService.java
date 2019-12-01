package com.m.paas.dandelion.admin.ops.server.service;

import com.m.paas.dandelion.admin.ops.server.domain.Server;
import java.util.List;

/**
 * 服务器Service接口
 * 
 * @author admin
 * @date 2019-11-14
 */
public interface IServerService 
{
    /**
     * 查询服务器
     * 
     * @param id 服务器ID
     * @return 服务器
     */
    public Server selectServerById(Long id);

    /**
     * 查询服务器列表
     * 
     * @param server 服务器
     * @return 服务器集合
     */
    public List<Server> selectServerList(Server server);

    /**
     * 新增服务器
     * 
     * @param server 服务器
     * @return 结果
     */
    public int insertServer(Server server);

    /**
     * 修改服务器
     * 
     * @param server 服务器
     * @return 结果
     */
    public int updateServer(Server server);

    /**
     * 批量删除服务器
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteServerByIds(String ids);

    /**
     * 删除服务器信息
     * 
     * @param id 服务器ID
     * @return 结果
     */
    public int deleteServerById(Long id);
}
