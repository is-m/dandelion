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
import com.m.paas.dandelion.ops.domain.Host;
import com.m.paas.dandelion.ops.service.IHostService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 服务器Controller
 * 
 * @author admin
 * @date 2019-12-10
 */
@Controller
@RequestMapping("/ops/host")
public class HostController extends BaseController {
	private String prefix = "ops/host";

	@Autowired
	private IHostService hostService;

	@RequiresPermissions("ops:host:view")
	@GetMapping()
	public String host() {
		return prefix + "/host";
	}

	/**
	 * 查询服务器列表
	 */
	@RequiresPermissions("ops:host:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Host host) {
		startPage();
		List<Host> list = hostService.selectHostList(host);
		return getDataTable(list);
	}

	/**
	 * 导出服务器列表
	 */
	@RequiresPermissions("ops:host:export")
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(Host host) {
		List<Host> list = hostService.selectHostList(host);
		ExcelUtil<Host> util = new ExcelUtil<Host>(Host.class);
		return util.exportExcel(list, "host");
	}

	/**
	 * 新增服务器
	 */
	@GetMapping("/add")
	public String add() {
		return prefix + "/add";
	}

	/**
	 * 新增保存服务器
	 */
	@RequiresPermissions("ops:host:add")
	@Log(title = "服务器", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Host host) {
		return toAjax(hostService.insertHost(host));
	}

	/**
	 * 修改服务器
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap) {
		Host host = hostService.selectHostById(id);
		mmap.put("host", host);
		return prefix + "/edit";
	}

	/**
	 * 修改保存服务器
	 */
	@RequiresPermissions("ops:host:edit")
	@Log(title = "服务器", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Host host) {
		return toAjax(hostService.updateHost(host));
	}

	/**
	 * 删除服务器
	 */
	@RequiresPermissions("ops:host:remove")
	@Log(title = "服务器", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	public AjaxResult remove(String ids) {
		return toAjax(hostService.deleteHostByIds(ids));
	}
}
