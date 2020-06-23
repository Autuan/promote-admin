package com.autuan.project.promote.task.service;

import com.autuan.project.promote.param.domain.TabParam;
import com.autuan.project.promote.task.domain.SetTaskParamAO;
import org.springframework.web.bind.annotation.RequestBody;

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

    /**
     * 获取设置的配置
     * @param
     * @throws
     * @author : Autuan.Yu
     * @return: java.util.List<com.autuan.project.promote.param.domain.TabParam>
     * @since : 2020/6/23 14:49
     */
    List<TabParam> getParamList(String taskId);
}
