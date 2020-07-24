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
            HttpServletResponse response)  {
        try{
            DataDownReq req = new DataDownReq();
            List<String> idList = Arrays.asList(ids.split(","));
            req.setIds(idList);
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
            response.setHeader("Content-Disposition","attachment;filename=test.xlsx");
            writer.flush(out);
            writer.close();
            IoUtil.close(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        return AjaxResult.success();
    }

    /**
     * 设置excel标题及表头
     * @param objs
     * @param dataList
     * @param params
     * @param cityId
     * @param year
     */
    public void createExcel(Object[] objs,List<Object[]>  dataList,Map<String,Object> params,String cityId,String year){
            title =   "test市"+ year +"年成绩清单";
            params.put("cityName", "test");
        rowsName = new String[]{"序号","项目","分项","考核内容","考核内容得分","评分人","分项得分","大项得分"};
//        List<CityAssess> cityScoreList = cityAssessService.getCityScoreList(cityId,year);
//        for(int i = 0 ; i < cityScoreList.size(); i ++){
//            CityAssess assess = cityScoreList.get(i);
//            objs = new Object[rowsName.length];
//            objs[0] = i;
//            objs[1] = assess.getFirstItemName();
//            objs[2] = assess.getSecondItemName();
//            objs[3] = assess.getThirdItemName();
//            if(null == String.valueOf(assess.getScore())){
//                objs[4] = 0 + "/" + assess.getTotal();
//            }else{
//                objs[4] = assess.getScore() + "/" + assess.getTotal();
//            }
//            objs[5] = assess.getMarkerName();
//            if(null == String.valueOf(assess.getSecondItemScore())){
//                objs[6] = 0;
//            }else {
//                objs[6] = assess.getSecondItemScore();
//            }
//            if(null == String.valueOf(assess.getFirstItemScore())){
//                objs[7] = 0;
//            }else {
//                objs[7] = assess.getFirstItemScore();
//            }

            dataList.add(objs);
//        }

        //年度总分
//        String yearScore = null;
//        YearCityAssess yearAssessInfo = cityAssessService.getYearAssessInfo(cityId,year);
//        if(null != yearAssessInfo){
//            yearScore = String.valueOf(yearAssessInfo.getScore());
//        }
        params.put("yearScore", "80");
        params.put("year", year);
    }
}
