package pers.java.user.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import pers.java.user.advice.exception.children.ServerException;
import pers.java.user.common.utils.MybatisUtils;
import pers.java.user.config.util.PageUtil;
import pers.java.user.controller.dto.LoginDto;
import pers.java.user.controller.dto.UserSearchDto;
import pers.java.user.controller.vo.UserVo;
import pers.java.user.domain.User;
import pers.java.user.service.DepartmentService;
import pers.java.user.service.RoleService;
import pers.java.user.service.UserService;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
@Api(tags = "用户")
public class UserController {

    @Resource
    UserService userService;
    @Resource
    DepartmentService departmentService;

    @Resource
    RoleService roleService;

    @PostMapping("/search")
    public IPage<UserVo> search(UserSearchDto searchDto) {
        Page<User> page = userService.lambdaQuery().like(StrUtil.isNotBlank(searchDto.getName()), User::getUsername, searchDto.getName())
                .like(StrUtil.isNotBlank(searchDto.getPhone()), User::getPhone, searchDto.getPhone())
                .eq(ObjectUtil.isNotNull(searchDto.getStatus()), User::getStatus, searchDto.getStatus())
                .ge(ObjectUtil.isNotNull(searchDto.getStartDate()), User::getCreateTime, searchDto.getStartDate())
                .le(ObjectUtil.isNotNull(searchDto.getEndDate()), User::getCreateTime, searchDto.getEndDate()).page(MybatisUtils.initPage(searchDto));
        return PageUtil.copy(page, UserVo.class, userVo -> {
            userVo.setDepartment(departmentService.getById(userVo.getDepartmentId()))
                    .setRole(roleService.getById(userVo.getRoleId()));
        });
    }

    @PostMapping("/login")
    public UserVo login(@RequestBody LoginDto loginDto) {
        UserVo userVo = BeanUtil.copyProperties(userService.lambdaQuery().eq(User::getEmail, loginDto.getEmail()).eq(User::getPassword, loginDto.getPassword()).one(), UserVo.class);
        return userVo.setRole(roleService.getById(userVo.getRoleId()))
                .setDepartment(departmentService.getById(userVo.getDepartmentId()));
    }


    @PostMapping("/save")
    public User save(@RequestBody User user) {
        User one = userService.lambdaQuery().eq(User::getEmail, user.getEmail()).one();
        if (one == null) {
            throw new ServerException("邮箱已存在");
        }
        userService.save(user);
        return user;
    }

    @PutMapping("/update")
    public User update(@RequestBody User user) {
        userService.updateById(user);
        return user;
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {
        userService.removeById(id);
    }
}
