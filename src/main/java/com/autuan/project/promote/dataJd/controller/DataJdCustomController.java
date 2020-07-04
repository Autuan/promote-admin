package com.autuan.project.promote.dataJd.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.autuan.common.utils.excel.ExcelRead;
import com.autuan.common.utils.poi.ExcelUtil;
import com.autuan.framework.config.RuoYiConfig;
import com.autuan.framework.web.domain.AjaxResult;
import com.autuan.project.front.entity.ReturnResult;
import com.autuan.project.promote.dataJd.domain.DataJd;
import com.autuan.project.promote.dataJd.domain.OptionJdRewardReq;
import com.autuan.project.promote.dataJd.domain.TabDataJd;
import com.autuan.project.promote.dataJd.service.IDataJdCustomService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author : Autuan.Yu
 * @description :  描述
 * @remark : 备注
 * @date : 2020/7/1 11:10
 * @company : 上海奥若拉信息科技集团有限公司
 */
@Slf4j
@Controller
@RequestMapping("/promote/dataJd/custom")
public class DataJdCustomController {
    @Autowired
    private IDataJdCustomService dataJdCustomService;
    @RequestMapping("/importExcel")
    @ResponseBody
    public AjaxResult importExcel(HttpServletRequest request) throws ParseException {
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

        //导入有误的条目
        List<String> errorList = Lists.newArrayList();
        //获取管理员id
        //对导入的集合进行处理,第一行为标题
        if (!CollectionUtils.isEmpty(inputList) && inputList.size() > 1) {
            List<TabDataJd> list = new ArrayList<>();
            for (int i = 1; i < inputList.size(); i++) {
                List<Object> objList = inputList.get(i);
                if (CollectionUtil.isNotEmpty(objList)) {
                    int j = 0;
                    ExcelRead.setObjList(objList);
                    LocalDateTime recordTime =  ExcelRead.getLocalDateTime(j++);
                    String taskInnerId = (String) objList.get(j++);
                    String taskUrl = (String) objList.get(j++);
                    String orderNo = (String) objList.get(j++);
                    String orderName = (String) objList.get(j++);
                    String channelFirst = String.valueOf(objList.get(j++));
                    String channelBelowSource = (String) objList.get(j++);
                    String checkStatusStr = (String) objList.get(j++);
                    String checkReason = (String) objList.get(j++);

                    String joinJdPin = String.valueOf(objList.get(j++));
                    String joinLink = String.valueOf(objList.get(j++));
                    LocalDateTime joinTime =  ExcelRead.getLocalDateTime(j++);
                    String joinOrder = String.valueOf(objList.get(j++));
                    String openJdCreditPin = String.valueOf(objList.get(j++));
                    String openJdCreditUrl = ExcelRead.getStr(j++);
                    LocalDateTime openJdCreditTime =  ExcelRead.getLocalDateTime(j++);
                    String openJdCreditTypeStr = ExcelRead.getStrDef(j++,"");
                    Integer openJdCreditType = 0;
                    switch (openJdCreditTypeStr) {
                        case "普通开白条" : openJdCreditType=0;break;
                        case "小金库白条" : openJdCreditType=1;break;
                        case "新手礼包" : openJdCreditType=2;break;
                        default:break;
                    }
                    String jdCreditFirstOrderNo = ExcelRead.getStr(j++);
                    String jdCreditFirstOrderPin =ExcelRead.getStr(j++);
                    LocalDateTime jdCreditFirstOrderTime =  ExcelRead.getLocalDateTime(j++);
                    String jdCreditFirstOrderUrl = ExcelRead.getStr(j++);
                    String bankAndOrderNo = ExcelRead.getStr(j++);
                    String bankAndPin = ExcelRead.getStr(j++);
                    String bankAndIncomeMoney = ExcelRead.getStr(j++);
                    LocalDateTime bankAndIncomeTime =  ExcelRead.getLocalDateTime(j++);
                    String bankAndPromoteUrl = ExcelRead.getStr(j++);
                    String joinedFirstOrderWithJdCreditTimeDifference = ExcelRead.getStr(j++);
                    String joinedFirstOrderWithBankIncomeTimeDifference =ExcelRead.getStr(j++);
                    String jdCreditOpenOneDayStr = ExcelRead.getStr(j++);
                    LocalDateTime newbiePackageOperTime =  ExcelRead.getLocalDateTime(j++);
                    String newbiePackagePin = ExcelRead.getStr(j++);
                    String newbiePackageUrlId = ExcelRead.getStr(j++);
                    String newbiePackageResultStr = ExcelRead.getStr(j++);
                    String jdGoldOrderNo = ExcelRead.getStr(j++);
                    String jdGoldIsFirstStr = ExcelRead.getStr(j++);
                    LocalDateTime jdGoldConfirmTime =  ExcelRead.getLocalDateTime(j++);
                    String jdNewHandRewardId = ExcelRead.getStr(j++);
//                    String taskId = (String) objList.get(9);
//                    String salesmanId = (String) objList.get(10);

                    list.add(TabDataJd.builder()
                            .recordTime(recordTime)
                            .taskInnerId(taskInnerId)
                            .taskUrl(taskUrl)
                            .orderNo(orderNo)
                            .orderName(orderName)
                            .channelFirst(channelFirst)
                            .channelBelowSource(channelBelowSource)
                            .checkStatus("是".equals(checkStatusStr) ? 1 : 0)
                            .checkReason(checkReason)
                            .joinJdPin(joinJdPin)
                            .joinLink(joinLink)
                            .joinTime(joinTime)
                            .joinOrder(joinOrder)
                            .openJdCreditPin(openJdCreditPin)
                            .openJdCreditUrl(openJdCreditUrl)
                            .openJdCreditTime(openJdCreditTime)
                            .openJdCreditType(openJdCreditType)
                            .jdCreditFirstOrderNo(jdCreditFirstOrderNo)
                            .jdCreditFirstOrderPin(jdCreditFirstOrderPin)
                            .jdCreditFirstOrderTime(jdCreditFirstOrderTime)
                            .jdCreditFirstOrderUrl(jdCreditFirstOrderUrl)
                            .bankAndOrderNo(bankAndOrderNo)
                            .bankAndPin(bankAndPin)
                            .bankAndIncomeMoney(bankAndIncomeMoney)
                            .bankAndIncomeTime(bankAndIncomeTime)
                            .bankAndPromoteUrl(bankAndPromoteUrl)
                            .joinedFirstOrderWithJdCreditTimeDifference(joinedFirstOrderWithJdCreditTimeDifference)
                            .joinedFirstOrderWithBankIncomeTimeDifference(joinedFirstOrderWithBankIncomeTimeDifference)
                            .jdCreditOpenOneDay("是".equals(jdCreditOpenOneDayStr) ? 1 : 0)
                            .newbiePackageOperTime(newbiePackageOperTime)
                            .newbiePackagePin(newbiePackagePin)
                            .newbiePackageUrlId(newbiePackageUrlId)
                            .newbiePackageResult("success".equals(newbiePackageResultStr) ? 1 : 0)
                            .jdGoldOrderNo(jdGoldOrderNo)
                            .jdGoldIsFirst("是".equals(jdGoldIsFirstStr) ? 1 : 0)
                            .jdGoldConfirmTime(jdGoldConfirmTime)
                            .jdNewHandRewardId(jdNewHandRewardId)
                            .build());
                }
            }
            dataJdCustomService.importExcel(list);
        }
        return AjaxResult.success(errorList);
    }


    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate() {
        ExcelUtil<DataJd> util = new ExcelUtil<DataJd>(DataJd.class);
        return util.importTemplateExcel("京东联合拉新");
    }

    @RequestMapping("/optionJdReward")
    @ResponseBody
    public ReturnResult optionJdReward(OptionJdRewardReq req) {
        dataJdCustomService.optionJdReward(req);
        return null;
    }
}
