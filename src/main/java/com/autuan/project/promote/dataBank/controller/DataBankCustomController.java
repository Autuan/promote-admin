package com.autuan.project.promote.dataBank.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.autuan.common.utils.excel.ExcelRead;
import com.autuan.common.utils.poi.ExcelUtil;
import com.autuan.framework.config.RuoYiConfig;
import com.autuan.framework.web.domain.AjaxResult;
import com.autuan.project.promote.dataBank.domain.DataBank;
import com.autuan.project.promote.dataBank.domain.TabDataBank;
import com.autuan.project.promote.dataBank.service.IDataBankCustomService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.*;

import static com.autuan.project.promote.base.constant.Constant.*;

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

    @PostMapping("/importData")
    @ResponseBody
    @Deprecated
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<DataBank> util = new ExcelUtil<DataBank>(DataBank.class);
        List<DataBank> list = util.importExcel(file.getInputStream());
        String message = dataBankCustomService.importData(list, updateSupport);
        return AjaxResult.success(message);
    }

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
        String message = "";
        //读取excel的结果
        List<List<Object>> inputList = Lists.newArrayList();
        try {
            if (ServletFileUpload.isMultipartContent(request)) {
                String excelTempPath = RuoYiConfig.getProfile();
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

        //获取管理员id
        //对导入的集合进行处理,第一行为标题
        if (!CollectionUtils.isEmpty(inputList) && inputList.size() > 1) {
            List<TabDataBank> list = new ArrayList<>();
            for (int i = 1; i < inputList.size(); i++) {
                List<Object> objList = inputList.get(i);
                if (CollectionUtil.isNotEmpty(objList)) {
                    int j = 0;
                    ExcelRead.setObjList(objList);
                    LocalDateTime applyDate = ExcelRead.getLocalDateTime(j++);
                    LocalDateTime verifyDate = ExcelRead.getLocalDateTime(j++);
                    String applyId = ExcelRead.getStr(j++);
                    String approveStatusStr = ExcelRead.getStr(j++);
                    String bankName = ExcelRead.getStr(j++);
                    String cMobile =ExcelRead.getStr(j++);
                    String cName =ExcelRead.getStr(j++);
                    String channelCode = ExcelRead.getStr(j++);
                    String customFlagStr = ExcelRead.getStr(j++);
                    list.add(TabDataBank.builder()
                            .applyDate(applyDate)
                            .verifyDate(verifyDate)
                            .applyId(applyId)
                            .approveStatus("通过".equals(approveStatusStr) ? YES : NO)
                            .bankName(bankName)
                            .cMobile(cMobile)
                            .cName(cName)
                            .channelCode(channelCode)
                            .customFlag("是".equals(customFlagStr) ? YES : NO)
                            .build());
                }
            }
             message=   dataBankCustomService.importExcel(list);
        }
        return AjaxResult.success(message);
    }
}