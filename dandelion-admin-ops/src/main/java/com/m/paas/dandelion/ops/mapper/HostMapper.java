package com.m.paas.dandelion.ops.mapper;

import com.m.paas.dandelion.ops.domain.Host;
import java.util.List;

/**
 * 服务器Mapper接口
 * 
 * @author admin
 * @date 2019-12-10
 */
public interface HostMapper {
	/**
	 * 查询服务器
	 * 
	 * @param id 服务器ID
	 * @return 服务器
	 */
	public Host selectHostById(Long id);

	/**
	 * 查询服务器列表
	 * 
	 * @param host 服务器
	 * @return 服务器集合
	 */
	public List<Host> selectHostList(Host host);

	/**
	 * 新增服务器
	 * 
	 * @param host 服务器
	 * @return 结果
	 */
	public int insertHost(Host host);

	/**
	 * 修改服务器
	 * 
	 * @param host 服务器
	 * @return 结果
	 */
	public int updateHost(Host host);

	/**
	 * 删除服务器
	 * 
	 * @param id 服务器ID
	 * @return 结果
	 */
	public int deleteHostById(Long id);

	/**
	 * 批量删除服务器
	 * 
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	public int deleteHostByIds(String[] ids);
}
