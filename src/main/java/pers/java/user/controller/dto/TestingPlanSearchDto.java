package pers.java.user.controller.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import pers.java.user.common.utils.entity.PageParam;

import java.util.Date;

@Data
@Accessors(chain = true)
public class TestingPlanSearchDto implements PageParam {
    private String name;
    /**
     * 负责人
     */
    private Integer userId;

    /**
     *
     */
    private Date startTime;

    private Date endTime;

    private Integer pageSize;
    private Integer pageNumber;
}
