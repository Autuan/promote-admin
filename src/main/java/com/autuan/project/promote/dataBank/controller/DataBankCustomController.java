package com.autuan.project.promote.dataBank.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.autuan.common.exception.custom.CustomRespondException;
import com.autuan.common.utils.datetime.LocalDateTimeUtil;
import com.autuan.common.utils.excel.ExcelRead;
import com.autuan.common.utils.poi.ExcelUtil;
import com.autuan.common.utils.security.ShiroUtils;
import com.autuan.framework.web.domain.AjaxResult;
import com.autuan.project.front.entity.ReturnResult;
import com.autuan.project.promote.dataBank.domain.DataBank;
import com.autuan.project.promote.dataBank.domain.TabDataBank;
import com.autuan.project.promote.dataBank.service.IDataBankCustomService;
import com.autuan.project.system.user.domain.User;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @className: DataBankCustomController
 * @author: sen.zhou
 * @description:
 * @date: 2020/6/25 20:53
 * @version: V2.0.0
 * @company:上海奥若拉信息科技集团有限公司
 **/
@Controller
@Slf4j
@RequestMapping("/promote/dataBank/custom")
public class DataBankCustomController {

    @Autowired
    private IDataBankCustomService dataBankCustomService;

    //    @Log(title = "用户管理", businessType = BusinessType.IMPORT)
//    @RequiresPermissions("system:user:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<DataBank> util = new ExcelUtil<DataBank>(DataBank.class);
        List<DataBank> list = util.importExcel(file.getInputStream());
        String message = dataBankCustomService.importData(list, updateSupport);
        return AjaxResult.success(message);
    }

    //    @RequiresPermissions("system:user:view")
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate() {
        ExcelUtil<DataBank> util = new ExcelUtil<DataBank>(DataBank.class);
        return util.importTemplateExcel("开卡数据");
    }

    /***
     * 导入excel
     * @param
     * @throws Throwable
     * @description:
     * @author: sen.zhou
     * @return : com.autuan.project.front.entity.ReturnResult
     * @since: 20:55 2020/6/25
     */
    @RequestMapping("/importExcel")
    @ResponseBody
    public AjaxResult importExcel(HttpServletRequest request) throws ParseException {
        //读取excel的结果
        List<List<Object>> inputList = Lists.newArrayList();
        try {
            if (ServletFileUpload.isMultipartContent(request)) {
                ServletContext application = request.getSession().getServletContext();
//                String excelTempPath = application.getRealPath("/");
//                String excelTempPath = application.getRealPath("d:/tmp/");
                String excelTempPath = "d:/tmp/";
                // 检查目录
                File uploadDir = new File(excelTempPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }
                CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getServletContext());
                if (multipartResolver.isMultipart(request)) {
                    MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
                    Map<String, MultipartFile> fms = multipartRequest.getFileMap();
                    for (Map.Entry<String, MultipartFile> entity : fms.entrySet()) {
                        MultipartFile mf = entity.getValue();
                        String fileName = mf.getOriginalFilename();
                        // 1.把excel文件，存储在临时文件中 2.调用excelUtil进行读取操作 3.最总清空临时文件
                        String filePath = excelTempPath + fileName;
                        File fileTemp = new File(filePath);
                        FileCopyUtils.copy(mf.getBytes(), fileTemp);
                        inputList = ExcelRead.exportListFromExcel(fileTemp, 0);
                        fileTemp.delete();
                    }
                } else {
                    return AjaxResult.error("请求出现错误，请稍后再试！");
                }
            } else {
                return AjaxResult.error("请求出现错误，请稍后再试！");
            }
        } catch (IOException e) {
            log.error("从excel表格中批量导入品牌运营商异常===》{}", e);
            return AjaxResult.error(e.getMessage());
        }

        //导入有误的条目
        List<String> errorList = Lists.newArrayList();
        //获取管理员id
        //对导入的集合进行处理,第一行为标题
        if (!CollectionUtils.isEmpty(inputList) && inputList.size() > 1) {
            List<TabDataBank> list = new ArrayList<>();
            for (int i = 1; i < inputList.size(); i++) {
                List<Object> objList = inputList.get(i);
                if (CollectionUtil.isNotEmpty(objList)) {
                    LocalDateTime applyDate = (LocalDateTime) objList.get(0);
                    LocalDateTime verifyDate = (LocalDateTime) objList.get(1);
                    String applyId = (String) objList.get(2);
                    String approveStatusStr = (String) objList.get(3);
                    String bankName = (String) objList.get(4);
                    String cMobile = String.valueOf(objList.get(5));
                    String cName = (String) objList.get(6);
                    String channelCode = (String) objList.get(7);
                    String customFlagStr = (String) objList.get(8);

                    list.add(TabDataBank.builder()
                            .applyDate(applyDate)
                            .verifyDate(verifyDate)
                            .applyId(applyId)
                            .approveStatus(approveStatusStr.equals("通过") ? 1 : 0)
                            .bankName(bankName)
                            .cMobile(cMobile)
                            .cName(cName)
                            .channelCode(channelCode)
                            .customFlag(customFlagStr.equals("是") ? 1 : 0)
                            .build());
                }
            }
                dataBankCustomService.importExcel(list);
        }
        return AjaxResult.success(errorList);
    }
}