package pers.llz.user.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ma.glasnost.orika.MapperFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import pers.llz.user.advice.exception.children.BadRequestException;
import pers.llz.user.common.constant.DeletedFlag;
import pers.llz.user.common.constant.OperateTypeConstant;
import pers.llz.user.common.result.ResultCode;
import pers.llz.user.common.utils.MybatisUtils;
import pers.llz.user.config.mybatis.handler.CustomBaseMapper;
import pers.llz.user.controller.dto.DeleteUserDto;
import pers.llz.user.controller.dto.UserPageParam;
import pers.llz.user.domain.UserInfo;
import pers.llz.user.domain.UserInfoHistory;
import pers.llz.user.service.UserInfoHistoryService;
import pers.llz.user.service.UserInfoService;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@Api(tags = "用户管理")
public class UserController {
    @Resource
    UserInfoService userInfoService;

    @Resource
    UserInfoHistoryService userInfoHistoryService;

    @Resource
    MapperFacade mapperFacade;

    @PostMapping("/")
    @ApiOperation("保存用户")
    public UserInfo saveUser(@RequestBody UserInfo userInfo) {
        userInfoService.save(userInfo);
        return userInfo;
    }

    @PutMapping("/")
    @ApiOperation("更新用户")
    public void updateUser(@RequestBody UserInfo userInfo) {
        UserInfo old = userInfoService.getById(userInfo.getId());
        userInfoHistoryService.save(mapperFacade.map(old, UserInfoHistory.class)
                .setId(null)
                .setCreatedTime(null)
                .setUserId(userInfo.getId())
                .setOperateType(OperateTypeConstant.UPDATE));
        userInfoService.updateById(userInfo);
    }

    @GetMapping("/{id}")
    @ApiOperation("获取一个用户信息")
    public UserInfo getOneUser(@PathVariable Integer id) {
        return Optional.ofNullable(userInfoService.getById(id)).orElseThrow(() -> new BadRequestException(ResultCode.USER_ACCOUNT_NOT_EXIST));
    }

    @GetMapping("/page")
    @ApiOperation("分页搜索用户")
    public Page<UserInfo> getUsers(UserPageParam param) {
        return userInfoService.lambdaQuery()
                .likeRight(StringUtils.isNotBlank(param.getKeywords()), UserInfo::getName, param.getKeywords())
                .orderByDesc(UserInfo::getCreatedTime)
                .page(MybatisUtils.initPage(param));
    }

    @PutMapping("/delete")
    @ApiOperation("删除用户")
    public void deleteUser(@RequestBody @Valid DeleteUserDto dto) {
        for (Integer id : dto.getIds()) {
            UserInfo userInfo = userInfoService.getById(id);
            if (userInfo != null) {
                userInfoService.removeById(id);
                userInfoHistoryService.save(mapperFacade.map(userInfo, UserInfoHistory.class)
                        .setId(null)
                        .setCreatedTime(null)
                        .setUserId(userInfo.getId())
                        .setOperateType(OperateTypeConstant.DELETE));
            }
        }

    }

    @PutMapping("/revoke")
    public void revoke() {
        UserInfoHistory one = userInfoHistoryService.lambdaQuery().orderByDesc(UserInfoHistory::getCreatedTime).last("limit 1").one();
        if (OperateTypeConstant.DELETE.equals(one.getOperateType())) {
            ((CustomBaseMapper<?>) userInfoService.getBaseMapper()).recoverLogicDeleted(one.getUserId());
        } else if (OperateTypeConstant.UPDATE.equals(one.getOperateType())) {
            userInfoService.updateById(mapperFacade.map(one, UserInfo.class).setCreatedTime(null).setId(one.getUserId()));
        }
        userInfoHistoryService.removeById(one.getId());

    }

    @GetMapping("/{id}/history")
    public Page<UserInfoHistory> history(@PathVariable String id, Integer pageSize, Integer pageNumber) {
        return userInfoHistoryService.lambdaQuery()
                .eq(UserInfoHistory::getUserId, id)
                .eq(UserInfoHistory::getOperateType, OperateTypeConstant.UPDATE)
                .orderByDesc(UserInfoHistory::getCreatedTime)
                .page(MybatisUtils.initPage(pageNumber, pageSize));
    }

    @PutMapping("/revoke/{id}/history")
    public void revokeTo(@PathVariable Integer id) {
        UserInfoHistory userInfoHistory = userInfoHistoryService.getById(id);
        if (userInfoHistory == null) {
            throw new BadRequestException(ResultCode.COMMON_FAIL);
        }
        userInfoHistoryService.save(userInfoHistory.setCreatedTime(null).setId(null));
        userInfoService.updateById(mapperFacade.map(userInfoHistory, UserInfo.class).setCreatedTime(null).setId(userInfoHistory.getUserId()));
    }
}
