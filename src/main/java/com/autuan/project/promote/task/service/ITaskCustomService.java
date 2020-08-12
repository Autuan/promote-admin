package com.autuan.project.promote.task.service;

import com.autuan.project.front.entity.GeneratorQrCodeVO;
import com.autuan.project.front.entity.ReceiveAO;
import com.autuan.project.promote.link.linkSalesmanTask.domain.TabSalesmanTask;
import com.autuan.project.promote.param.domain.TabParam;
import com.autuan.project.promote.salesman.domain.TabSalesman;
import com.autuan.project.promote.task.domain.*;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.ServletOutputStream;
import java.util.List;

/**
 * @author : Autuan.Yu
 * @description :  描述
 * @remark : 备注
 * @date : 2020/6/23 14:40
 * @company : 上海奥若拉信息科技集团有限公司
 */
public interface ITaskCustomService {
    /**
     * 设置参数   
     * @param ao 
     * @throws 
     * @author : Autuan.Yu
     * @return: void
     * @since : 2020/6/23 14:45
     */
    void setTaskParam(SetTaskParamAO ao);

    /***
     *
     * @param taskId
     * @throws Throwable
     * @description:
     * @author: sen.zhou
     * @return : java.lang.Object
     * @since: 20:48 2020/7/2
     */
    List<TabSalesmanTask> listForCode(String taskId);
    /**
     * 获取设置的配置
     * @param
     * @throws
     * @author : Autuan.Yu
     * @return: java.util.List<com.autuan.project.promote.param.domain.TabParam>
     * @since : 2020/6/23 14:49
     */
    List<TabParam> getParamList(String taskId);

    /**
     * 获取首页task  
     * @param  
     * @throws 
     * @author : Autuan.Yu
     * @return: java.util.List<com.autuan.project.promote.task.domain.TabTask>
     * @since : 2020/6/23 16:30
     */
    List<TabTask> getIndexTask();
    
    /**
     * 生成二维码  
     * @param  
     * @throws 
     * @author : Autuan.Yu
     * @return: byte[]
     * @since : 2020/6/23 16:31  
     */
    void generatorQrcode(GeneratorQrCodeVO vo, ServletOutputStream outputStream);

    /***
     * 获取一千条任务
     * @param
     * @throws Throwable
     * @description:
     * @author: sen.zhou
     * @return : java.util.List<com.autuan.project.promote.task.domain.TabTask>
     * @since: 20:28 2020/6/23
     */
    List<TabTask> listTaskThousand();

    /***
     * 领取任务
     * @param ao 
     * @throws Throwable
     * @description:
     * @author: sen.zhou
     * @return : void
     * @since: 19:49 2020/6/28
     */
    void receive(ReceiveAO ao);

    /**
     * 批量领取  
     * @param ao 
     * @throws 
     * @author : Autuan.Yu
     * @return: void
     * @since : 2020/7/7 16:11
     */
    void batchReceive(ReceiveAO ao);
    /**
     * 已领取任务
     * @param salesmanId
     * @throws
     * @author : Autuan.Yu
     * @return: java.util.List<com.autuan.project.promote.link.linkSalesmanTask.domain.TabSalesmanTask>
     * @since : 2020/7/1 16:16
     */
    List<TabSalesmanTask> receivedTask(String salesmanId);

    void setCode(SetCodeReq req);

    /***
     * 添加
     * @param req 
     * @throws Throwable
     * @description:
     * @author: sen.zhou
     * @return : void
     * @since: 19:49 2020/7/4
     */
    void add(TaskAddReq req);

    /**
     * 获取未领取任务的业务员,最多50条
     * @param id
     * @throws
     * @author : Autuan.Yu
     * @return: java.util.List<com.autuan.project.promote.salesman.domain.TabSalesman>
     * @since : 2020/7/7 15:38
     */
    List<TabSalesman> getNotReceiveSalesmanByTaskId(String id);

    /***
     * 设置隐藏值
     * @param req
     * @throws Throwable
     * @description:
     * @author: sen.zhou
     * @return : com.autuan.project.promote.task.domain.Task
     * @since: 19:53 2020/8/4
     */
    int changeHiddenVal(ChangeHiddenValReq req);
}
