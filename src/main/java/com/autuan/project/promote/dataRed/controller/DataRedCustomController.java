package com.autuan.project.promote.dataRed.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.autuan.common.utils.excel.ExcelRead;
import com.autuan.common.utils.poi.ExcelUtil;
import com.autuan.framework.config.RuoYiConfig;
import com.autuan.framework.web.controller.BaseController;
import com.autuan.framework.web.domain.AjaxResult;
import com.autuan.project.promote.dataJd.domain.DataJd;
import com.autuan.project.promote.dataRed.domain.DataRed;
import com.autuan.project.promote.dataRed.domain.TabDataRed;
import com.autuan.project.promote.dataRed.service.IDataRedCustomService;
import com.google.common.collect.Lists;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
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
import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @className: DataRedCustomController
 * @author: sen.zhou
 * @description:
 * @date: 2020/9/13 21:10
 * @version: V2.0.0
 * @company:上海奥若拉信息科技集团有限公司
 **/
@Controller
@RequestMapping("/promote/dataRed/custom")
public class DataRedCustomController extends BaseController {
    @Autowired
    private IDataRedCustomService dataRedCustomService;

    @RequestMapping("/importExcel")
    @ResponseBody
    public AjaxResult importExcel(HttpServletRequest request) throws ParseException {
        // todo 查询列表时标注哪些数据没有对应业务员 [Autuan](20.10.31)
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
//            log.error("从excel表格中批量导入品牌运营商异常===》{}", e);
            e.printStackTrace();
            return AjaxResult.error(e.getMessage());
        }

        //对导入的集合进行处理,第一行为标题
        if (!CollectionUtils.isEmpty(inputList) && inputList.size() > 1) {
            List<TabDataRed> list = new ArrayList<>();
            for (int i = 1; i < inputList.size(); i++) {
                List<Object> objList = inputList.get(i);
                if (CollectionUtil.isNotEmpty(objList)) {
                    int j = 0;
                    ExcelRead.setObjList(objList);
//                    LocalDateTime applyDate = ExcelRead.getLocalDateTime(j++);
                    String redPin = ExcelRead.getStr(j++);
                    BigDecimal reward = ExcelRead.getBigDecimalDef(j++, BigDecimal.ZERO);
                    LocalDateTime recordTime = ExcelRead.getLocalDateTime(j++);
//                    String bankName = ExcelRead.getStr(j++);
//                    String cMobile =ExcelRead.getStr(j++);
//                    String cName =ExcelRead.getStr(j++);
//                    String channelCode = ExcelRead.getStr(j++);
//                    String customFlagStr = ExcelRead.getStr(j++);
                    list.add(TabDataRed.builder()
                            .redPin(redPin)
                            .recordTime(recordTime)
                            .reward(reward)
                            .build());
                }
            }
            message=   dataRedCustomService.importExcel(list);
        }
        return AjaxResult.success(message);
    }


    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate() {
        ExcelUtil<DataRed> util = new ExcelUtil<DataRed>(DataRed.class);
        return util.importTemplateExcel("京东红包码");
    }
}