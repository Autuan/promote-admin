package com.autuan.project.promote.dataBank.controller;

import cn.hutool.core.date.DateUtil;
import com.autuan.common.exception.custom.CustomRespondException;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<DataBank> util = new ExcelUtil<DataBank>(DataBank.class);
        List<DataBank> list = util.importExcel(file.getInputStream());
        String message = dataBankCustomService.importData(list, updateSupport);
        return AjaxResult.success(message);
    }

//    @RequiresPermissions("system:user:view")
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
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
    public ReturnResult importExcel(HttpServletRequest request){
        //读取excel的结果
        List<String> inputList = Lists.newArrayList();
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
                    return ReturnResult.error( "请求出现错误，请稍后再试！");
                }
            } else {
                return ReturnResult.error("请求出现错误，请稍后再试！");
            }
        } catch (IOException e) {
            log.error("从excel表格中批量导入品牌运营商异常===》{}", e);
            return ReturnResult.error(e.getMessage());
        }

        //导入有误的条目
        List<String> errorList = Lists.newArrayList();
        //获取管理员id
        String loginName = ShiroUtils.getLoginName();
        //对导入的集合进行处理,第一行为标题
        if(!CollectionUtils.isEmpty(inputList)&&inputList.size()>1){
            List<TabDataBank> list = new ArrayList<>();

            for(int i=1;i<inputList.size();i++){
                String model = inputList.get(i);
                String[] info = model.split("\\|");
                if(info.length > 0){
                    String applyDateStr = info[1];
//                    LocalDateTime test = new LocalDateTime(applyDateStr);
//                    new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US).parse("Tue Jul 23 00:00:00 CST 2019");
                    DateUtil.parse(applyDateStr)
                    DateTimeFormatter df = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss zzz yyyy");
                    LocalDateTime applyDate =LocalDateTime.parse(applyDateStr,df);
//                    String memberNo = info[1];
//                    String memberName = info[2];
//                    String memberMobile = info[3];
//                    String initMoney = info[4];


                    TabDataBank.builder()
                            .applyDate(applyDate)
                            .build();
//                    ImportCreditRoleAdminRequestModel requestModel = new ImportCreditRoleAdminRequestModel();
//                    requestModel.setMemberNo(memberNo);
//                    requestModel.setMemberName(memberName);
//                    requestModel.setMemberMobile(memberMobile);
//                    requestModel.setInitMoney(initMoney);
//                    requestModel.setRowNum(i);
//                    requestModel.setUserId(userId);

                }
            }

            try {
                dataBankCustomService.importExcel(list);
//                ResponseModel<BooleanResponseModel> responseModel = iMemberRoleAdminApi.importCreditInfoAdmin(requestModel);
//
//                if(!CommonConstant.OPERATE_SUC.equals(responseModel.getCode()) || !responseModel.getData().isValue()){
//                    errorList.add(responseModel.getMsg());
//                }
            } catch (CustomRespondException e){
                errorList.add(e.getMessage()+"，此条导入已忽略。");
//                errorList.add("第"+(i+1)+"行："+memberNo+","+e.getMessage()+"，此条导入已忽略。");
            }
        }
        int size=inputList.size()-1;
        errorList.add(0,"共 "+(size)+"条记录;成功 "+(size-errorList.size())+"条记录;失败 "+(errorList.size())+"条记录。");
        return ReturnResult.ok(errorList);
    }
}