package pers.java.user.controller.dto;

import lombok.Data;
import pers.java.user.common.utils.entity.PageParam;

@Data
public class TestingCaseSearchDto implements PageParam {
    /**
     * 用例计划id
     */
    private Integer plantId;

    /**
     * 用例标题
     */
    private String name;

    /**
     * 输出者
     */
    private Integer userId;


    private Integer pageSize;
    private Integer pageNumber;
}
