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
    public String rewardPage(String ids,ModelMap mmap) {
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

    @RequestMapping(value="/dataDown")
    public void dataDown(
//			@RequestBody DataDownReq req,
            String ids,
            String startTime,
            String endTime,
            HttpServletResponse response)  {
        try{

            List<String> idList = Arrays.asList(ids.split(","));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate start = LocalDate.parse(startTime, formatter);
            LocalDate end = LocalDate.parse(endTime, formatter);
            DataDownReq req = DataDownReq.builder()
                    .ids(idList)
                    .startTime(LocalDateTime.of(start, LocalTime.MIN))
                    .endTime(LocalDateTime.of(end,LocalTime.MAX))
                    .build();
//        MessageBean msg = new MessageBean();
//        Map<String,Object> params = new HashMap<String, Object>();
//        List<Object[]>  dataList = new ArrayList<Object[]>();
//        Object[] objs = null;
            ExcelWriter writer = (ExcelWriter) salesmanCustomService.dataDown(req);
            ServletOutputStream out = response.getOutputStream();
//        List<String> row1 = CollUtil.newArrayList("aa", "bb", "cc", "dd");
//        List<String> row2 = CollUtil.newArrayList("aa1", "bb1", "cc1", "dd1");
//        List<String> row3 = CollUtil.newArrayList("aa2", "bb2", "cc2", "dd2");
//        List<String> row4 = CollUtil.newArrayList("aa3", "bb3", "cc3", "dd3");
//        List<String> row5 = CollUtil.newArrayList("aa4", "bb4", "cc4", "dd4");
//
//        List<List<String>> rows = CollUtil.newArrayList(row1, row2, row3, row4, row5);

//        ExcelWriter writer = cn.hutool.poi.excel.ExcelUtil.getWriter(true);
            //合并单元格后的标题行，使用默认标题样式
//        writer.merge(row1.size() - 1, "测试标题");
//一次性写出内容，强制输出标题
//        writer.write(rows, true);

//关闭writer，释放内存


            response.setContentType("application/vnd.ms-excel;charset=utf-8");
//test.xls是弹出下载对话框的文件名，不能为中文，中文请自行编码
            String fileName = "account_"+startTime+"_to_"+endTime+".xlsx";
            response.setHeader("Content-Disposition","attachment;filename="+fileName);
            writer.flush(out);
            writer.close();
            IoUtil.close(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        return AjaxResult.success();
    }

    @RequestMapping(value="/querySalesmanReward")
@ResponseBody
    public ReturnResult querySalesmanReward(@RequestBody DataDownReq req) {
        return ReturnResult.ok(salesmanCustomService.querySalesmanReward(req));
    }
}
