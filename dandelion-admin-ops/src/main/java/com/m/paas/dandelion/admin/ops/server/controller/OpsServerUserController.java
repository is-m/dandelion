package com.m.paas.dandelion.admin.ops.server.controller;

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
import com.m.paas.dandelion.admin.ops.server.domain.ServerUser;
import com.m.paas.dandelion.admin.ops.server.service.IServerUserService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 服务器用户Controller
 * 
 * @author admin
 * @date 2019-11-14
 */
@Controller
@RequestMapping("/serverUser")
public class OpsServerUserController extends BaseController
{
    private String prefix = "serverUser";

    @Autowired
    private IServerUserService serverUserService;

    @RequiresPermissions("server:serverUser:view")
    @GetMapping()
    public String serverUser()
    {
        return prefix + "/serverUser";
    }

    /**
     * 查询服务器用户列表
     */
    @RequiresPermissions("server:serverUser:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ServerUser serverUser)
    {
        startPage();
        List<ServerUser> list = serverUserService.selectServerUserList(serverUser);
        return getDataTable(list);
    }

    /**
     * 导出服务器用户列表
     */
    @RequiresPermissions("server:serverUser:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ServerUser serverUser)
    {
        List<ServerUser> list = serverUserService.selectServerUserList(serverUser);
        ExcelUtil<ServerUser> util = new ExcelUtil<ServerUser>(ServerUser.class);
        return util.exportExcel(list, "serverUser");
    }

    /**
     * 新增服务器用户
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存服务器用户
     */
    @RequiresPermissions("server:serverUser:add")
    @Log(title = "服务器用户", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ServerUser serverUser)
    {
        return toAjax(serverUserService.insertServerUser(serverUser));
    }

    /**
     * 修改服务器用户
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ServerUser serverUser = serverUserService.selectServerUserById(id);
        mmap.put("serverUser", serverUser);
        return prefix + "/edit";
    }

    /**
     * 修改保存服务器用户
     */
    @RequiresPermissions("server:serverUser:edit")
    @Log(title = "服务器用户", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ServerUser serverUser)
    {
        return toAjax(serverUserService.updateServerUser(serverUser));
    }

    /**
     * 删除服务器用户
     */
    @RequiresPermissions("server:serverUser:remove")
    @Log(title = "服务器用户", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(serverUserService.deleteServerUserByIds(ids));
    }
}
