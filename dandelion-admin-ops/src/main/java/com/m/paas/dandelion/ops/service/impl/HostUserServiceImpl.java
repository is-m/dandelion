package com.m.paas.dandelion.ops.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.m.paas.dandelion.ops.mapper.HostUserMapper;
import com.m.paas.dandelion.ops.domain.HostUser;
import com.m.paas.dandelion.ops.service.IHostUserService;
import com.ruoyi.common.core.text.Convert;

/**
 * 服务器用户Service业务层处理
 * 
 * @author admin
 * @date 2019-12-10
 */
@Service
public class HostUserServiceImpl implements IHostUserService {
	@Autowired
	private HostUserMapper hostUserMapper;

	/**
	 * 查询服务器用户
	 * 
	 * @param id 服务器用户ID
	 * @return 服务器用户
	 */
	@Override
	public HostUser selectHostUserById(Long id) {
		return hostUserMapper.selectHostUserById(id);
	}

	/**
	 * 查询服务器用户列表
	 * 
	 * @param hostUser 服务器用户
	 * @return 服务器用户
	 */
	@Override
	public List<HostUser> selectHostUserList(HostUser hostUser) {
		return hostUserMapper.selectHostUserList(hostUser);
	}

	/**
	 * 新增服务器用户
	 * 
	 * @param hostUser 服务器用户
	 * @return 结果
	 */
	@Override
	public int insertHostUser(HostUser hostUser) {
		hostUser.setCreateTime(DateUtils.getNowDate());
		return hostUserMapper.insertHostUser(hostUser);
	}

	/**
	 * 修改服务器用户
	 * 
	 * @param hostUser 服务器用户
	 * @return 结果
	 */
	@Override
	public int updateHostUser(HostUser hostUser) {
		hostUser.setUpdateTime(DateUtils.getNowDate());
		return hostUserMapper.updateHostUser(hostUser);
	}

	/**
	 * 删除服务器用户对象
	 * 
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	@Override
	public int deleteHostUserByIds(String ids) {
		return hostUserMapper.deleteHostUserByIds(Convert.toStrArray(ids));
	}

	/**
	 * 删除服务器用户信息
	 * 
	 * @param id 服务器用户ID
	 * @return 结果
	 */
	@Override
	public int deleteHostUserById(Long id) {
		return hostUserMapper.deleteHostUserById(id);
	}
}
