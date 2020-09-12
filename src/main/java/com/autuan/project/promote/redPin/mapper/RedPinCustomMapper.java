package com.autuan.project.promote.redPin.mapper;

import com.autuan.project.promote.redPin.domain.CheckDO;
import com.autuan.project.promote.redPin.domain.TabRedPin;
import org.apache.ibatis.annotations.Param;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

public interface RedPinCustomMapper {
    /***
     * 检查是否可以删除
     * 如果 根据id
     * @param record 
     * @throws Throwable
     * @description:
     * @author: sen.zhou
     * @return : boolean
     * @since: 20:49 2020/9/12 
     */
    List<CheckDO> redPinDelCheck(@Param("list") @NotBlank List<String> redPinIds);
}
