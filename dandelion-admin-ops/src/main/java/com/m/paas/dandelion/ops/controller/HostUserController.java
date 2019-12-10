package com.m.paas.dandelion.ops.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.m.paas.dandelion.ops.domain.HostUser;
import com.m.paas.dandelion.ops.service.IHostUserService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 服务器用户Controller
 * 
 * @author admin
 * @date 2019-12-10
 */
@Controller
@RequestMapping("/ops/hostUser")
public class HostUserController extends BaseController {
	private String prefix = "ops/hostUser";

	@Autowired
	private IHostUserService hostUserService;

	@RequiresPermissions("ops:hostUser:view")
	@GetMapping()
	public String hostUser() {
		return prefix + "/hostUser";
	}

	/**
	 * 查询服务器用户列表
	 */
	@RequiresPermissions("ops:hostUser:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(HostUser hostUser) {
		startPage();
		List<HostUser> list = hostUserService.selectHostUserList(hostUser);
		return getDataTable(list);
	}

	/**
	 * 导出服务器用户列表
	 */
	@RequiresPermissions("ops:hostUser:export")
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(HostUser hostUser) {
		List<HostUser> list = hostUserService.selectHostUserList(hostUser);
		ExcelUtil<HostUser> util = new ExcelUtil<HostUser>(HostUser.class);
		return util.exportExcel(list, "hostUser");
	}

	/**
	 * 新增服务器用户
	 */
	@GetMapping("/add")
	public String add() {
		return prefix + "/add";
	}

	/**
	 * 新增保存服务器用户
	 */
	@RequiresPermissions("ops:hostUser:add")
	@Log(title = "服务器用户", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(HostUser hostUser) {
		return toAjax(hostUserService.insertHostUser(hostUser));
	}

	/**
	 * 修改服务器用户
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap) {
		HostUser hostUser = hostUserService.selectHostUserById(id);
		mmap.put("hostUser", hostUser);
		return prefix + "/edit";
	}

	/**
	 * 修改保存服务器用户
	 */
	@RequiresPermissions("ops:hostUser:edit")
	@Log(title = "服务器用户", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(HostUser hostUser) {
		return toAjax(hostUserService.updateHostUser(hostUser));
	}

	/**
	 * 删除服务器用户
	 */
	@RequiresPermissions("ops:hostUser:remove")
	@Log(title = "服务器用户", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	public AjaxResult remove(String ids) {
		return toAjax(hostUserService.deleteHostUserByIds(ids));
	}
}
