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
import com.autuan.project.promote.task.domain.TaskEnum;
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
        String msg = "";
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
            List<TabDataJd> list = new ArrayList<>();
            for (int i = 1; i < inputList.size(); i++) {
                List<Object> objList = inputList.get(i);
                if (CollectionUtil.isNotEmpty(objList)) {
                    int j = 0;
                    ExcelRead.setObjList(objList);
                    // 任务名称
                    String orderName = (String) objList.get(j++);
                    // 下游渠道
                    String channelBelow = (String) objList.get(j++);
                    // 参与推广链接
                    String joinLink = String.valueOf(objList.get(j++));
                    // 业务类型
                    String openJdCreditTypeStr = ExcelRead.getStrDef(j++,"");
                    Integer openJdCreditType = null;
                    switch (openJdCreditTypeStr) {
                        case "普通开白条" : openJdCreditType= TaskEnum.JD_COMMON.val();break;
                        case "小金库白条" : openJdCreditType=TaskEnum.JD_GOLD.val();break;
                        case "新手礼包" : openJdCreditType=TaskEnum.JD_NEWBIE.val();break;
                        default:break;
                    }
                    // 白条开通PIN
                    String openJdCreditPin = String.valueOf(objList.get(j++));
                    // 白条开通时间
                    LocalDateTime openJdCreditTime =  ExcelRead.getLocalDateTime(j++);

                    list.add(TabDataJd.builder()
                            .channelBelow(channelBelow)
                            .orderName(orderName)
                            .joinLink(joinLink)
                            .openJdCreditPin(openJdCreditPin)
                            .openJdCreditTime(openJdCreditTime)
                            .openJdCreditType(openJdCreditType)
                            .build());
                }
            }
            msg = dataJdCustomService.importExcel(list);
        }
        return AjaxResult.success(msg);
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
