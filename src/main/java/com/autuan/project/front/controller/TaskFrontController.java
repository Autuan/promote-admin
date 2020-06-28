package com.autuan.project.front.controller;

import com.autuan.project.front.entity.GeneratorQrCodeVO;
import com.autuan.project.front.entity.ReceiveAO;
import com.autuan.project.front.entity.ReturnResult;
import com.autuan.project.promote.task.service.ITaskCustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author : Autuan.Yu
 * @description :  描述
 * @remark : 备注
 * @date : 2020/6/23 16:22
 * @company : 上海奥若拉信息科技集团有限公司
 */
@RestController
@RequestMapping("/front/task")
public class TaskFrontController {
    @Autowired
    ITaskCustomService taskCustomService;

    /***
     * 获取二维码
     * @param taskId
 * @param response 
     * @throws Throwable
     * @description:
     * @author: sen.zhou
     * @return : void
     * @since: 19:32 2020/6/23
     */
    @RequestMapping(value = "/qrcode/{taskId}/{salesmanId}",produces = MediaType.IMAGE_JPEG_VALUE)
//    @ResponseBody
    public void qrcode(@PathVariable("taskId") String taskId,
                       @PathVariable("salesmanId") String salesmanId,
                       HttpServletResponse response) throws IOException {
        ServletOutputStream outputStream = response.getOutputStream();
//        return taskCustomService.generatorQrcode(outputStream);
        GeneratorQrCodeVO vo = GeneratorQrCodeVO.builder()
                .salesmanId(salesmanId)
                .taskId(taskId)
                .build();
        taskCustomService.generatorQrcode(vo,outputStream);
    }

    /***
     * 领取任务
     * @param
     * @throws Throwable
     * @description:
     * @author: sen.zhou
     * @return : com.autuan.project.front.entity.ReturnResult
     * @since: 19:33 2020/6/28
     */
    @PostMapping("/receive")
    @ResponseBody
    public ReturnResult receive(@RequestBody ReceiveAO ao){
        taskCustomService.receive(ao);
        return ReturnResult.ok();

    }
}
