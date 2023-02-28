package pers.llz.user.controller.dto;

import lombok.Data;
import pers.llz.user.common.utils.entity.PageParam;

@Data
public class UserPageParam implements PageParam {
    Integer pageNumber;
    Integer pageSize;
    String keywords;
}
