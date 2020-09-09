package com.autuan.project.promote.salesman.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.autuan.common.utils.file.FileUtils;
import com.autuan.common.utils.poi.ExcelUtil;
import com.autuan.framework.aspectj.lang.annotation.Log;
import com.autuan.framework.aspectj.lang.enums.BusinessType;
import com.autuan.framework.web.controller.BaseController;
import com.autuan.framework.web.domain.AjaxResult;
import com.autuan.framework.web.page.TableDataInfo;
import com.autuan.project.front.entity.ReturnResult;
import com.autuan.project.promote.dataBank.domain.DataBank;
import com.autuan.project.promote.salesman.domain.DataDownReq;
import com.autuan.project.promote.salesman.domain.Salesman;
import com.autuan.project.promote.salesman.domain.SalesmanQueryReq;
import com.autuan.project.promote.salesman.domain.TabSalesman;
import com.autuan.project.promote.salesman.service.ISalesmanCustomService;
import com.autuan.project.promote.salesman.service.ISalesmanService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.autuan.project.promote.base.constant.Constant.*;

/**
 * 业务员Controller
 *
 * @author autuan
 * @date 2020-06-19
 */
@Controller
@RequestMapping("/promote/salesman/custom")
public class SalesmanCustomController extends BaseController {
    private String prefix = "promote/salesman";
    @Autowired
    private ISalesmanService salesmanService;
    @Autowired
    private ISalesmanCustomService salesmanCustomService;
    private static String title;

    private static String[] rowsName;


    @GetMapping("/rewardPage")
    public String rewardPage(String ids, ModelMap mmap) {
        mmap.put("ids", ids);
        return prefix + "/rewardPage";
    }

    /**
     * 查询业务员
     */
    @RequiresPermissions("promote:salesman:list")
    @PostMapping("/selectByMobile/{mobile}")
    @ResponseBody
    public ReturnResult selectByMobile(@PathVariable("mobile") String mobile) {
        TabSalesman tabSalesman = salesmanCustomService.selectByMobile(mobile);
        return ReturnResult.ok(tabSalesman);
    }

    /**
     * 查询业务员列表
     */
    @RequiresPermissions("promote:salesman:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SalesmanQueryReq salesman) {
        startPage();
        List<TabSalesman> list = salesmanCustomService.list(salesman);
        return getDataTable(list);
    }

    /**
     * 查询业务员列表
     */
    @PostMapping("/listForSelect")
    @ResponseBody
    public ReturnResult listForSelect(SalesmanQueryReq salesman) {
        List<TabSalesman> list = salesmanCustomService.list(salesman);
        return ReturnResult.ok(list);
    }

    @RequiresPermissions("promote:salesman:edit")
    @PostMapping("/resetPwd")
    @Log(title = "业务员重置密码", businessType = BusinessType.UPDATE)
    @ResponseBody
    public ReturnResult resetPwd(String ids) {
        salesmanCustomService.resetPwd(ids);
        return ReturnResult.ok();
    }

    @RequestMapping("/get/{id}")
    @ResponseBody
    public ReturnResult get(@PathVariable("id") String id) {
        Salesman salesman = salesmanService.selectSalesmanById(id);
        return ReturnResult.ok(salesman);
    }

    @PostMapping("/importExcel")
    @ResponseBody
    public AjaxResult importExcel(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<Salesman> util = new ExcelUtil<Salesman>(Salesman.class);
        List<Salesman> list = util.importExcel(file.getInputStream());
        String message = salesmanCustomService.importData(list, updateSupport);
        return AjaxResult.success(message);
    }

    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate() {
        ExcelUtil<Salesman> util = new ExcelUtil<Salesman>(Salesman.class);
        return util.importTemplateExcel("业务员");
    }

    /**
     * 下载选中业务员业绩
     *
     * @param ids
     * @param startTime
     * @param endTime
     * @param response
     */
    @RequestMapping(value = "/dataDown")
    public void dataDown(
            String ids,
            String startTime,String endTime,
            HttpServletResponse response) {
        try {
            List<String> idList = Arrays.asList(ids.split(","));
            LocalDate start = LocalDate.parse(startTime, FORMATTER_YMD);
            LocalDate end = LocalDate.parse(endTime, FORMATTER_YMD);
            DataDownReq req = DataDownReq.builder()
                    .ids(idList)
                    .startTime(LocalDateTime.of(start,MIN_TIME))
                    .endTime(LocalDateTime.of(end, MAX_TIME))
                    .build();
            ExcelWriter writer =  salesmanCustomService.dataDown(req);
            ServletOutputStream out = response.getOutputStream();


            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            String fileName = "account_" + startTime + "_to_" + endTime + ".xlsx";
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            writer.flush(out);
            writer.close();
            IoUtil.close(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 下载全部业务员业绩
     *
     * @param ids
     * @param startTime
     * @param endTime
     * @param response
     */
    @RequestMapping(value = "/dataDownAll")
    public void dataDownAll(
            String ids,
            String startTime,String endTime,
            HttpServletResponse response) {
        try {
            List<String> idList = Arrays.asList(ids.split(","));
            LocalDate start = LocalDate.parse(startTime, FORMATTER_YMD);
            LocalDate end = LocalDate.parse(endTime, FORMATTER_YMD);
            DataDownReq req = DataDownReq.builder()
                    .ids(idList)
                    .startTime(LocalDateTime.of(start, LocalTime.of(0,0,0)))
                    .endTime(LocalDateTime.of(end, LocalTime.of(23,59,59)))
                    .build();
            ExcelWriter writer = salesmanCustomService.dataDownAll(req);
            ServletOutputStream out = response.getOutputStream();


            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            String fileName = "all_" + startTime + "_to_" + endTime + ".xlsx";
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            writer.flush(out);
            writer.close();
            IoUtil.close(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 查询选中业务员业绩
     *
     * @param req
     * @throws
     * @author : Autuan.Yu
     * @return: com.autuan.project.front.entity.ReturnResult
     * @since : 2020/7/26 10:21
     */
    @RequestMapping(value = "/querySalesmanReward")
    @ResponseBody
    public ReturnResult querySalesmanReward(@RequestBody DataDownReq req) {
        return ReturnResult.ok(salesmanCustomService.querySalesmanReward(req));
    }


    /**
     * 查询全部业务员业绩
     *
     * @param req
     * @throws
     * @author : Autuan.Yu
     * @return: com.autuan.project.front.entity.ReturnResult
     * @since : 2020/7/26 11:07
     */
    @RequestMapping(value = "/querySalesmanRewardAll")
    @ResponseBody
    public ReturnResult querySalesmanRewardAll(@RequestBody DataDownReq req) {
        return ReturnResult.ok(salesmanCustomService.querySalesmanRewardAll(req));
    }

    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TabSalesman salesman) {
        return toAjax(salesmanCustomService.updateSalesman(salesman));
    }
}
