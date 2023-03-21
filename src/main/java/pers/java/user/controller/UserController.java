package pers.java.user.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.lang.generator.SnowflakeGenerator;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.crypto.digest.MD5;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import ma.glasnost.orika.MapperFacade;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pers.java.user.advice.exception.children.ServerException;
import pers.java.user.common.utils.MybatisUtils;
import pers.java.user.config.util.PageUtil;
import pers.java.user.controller.dto.LoginDto;
import pers.java.user.controller.dto.UserExcelExport;
import pers.java.user.controller.dto.UserExcelImport;
import pers.java.user.controller.dto.UserSearchDto;
import pers.java.user.controller.vo.UserVo;
import pers.java.user.domain.Role;
import pers.java.user.domain.User;
import pers.java.user.service.DepartmentService;
import pers.java.user.service.RoleService;
import pers.java.user.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import java.util.stream.Collectors;

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

    @Resource
    MapperFacade mapperFacade;

    @PostMapping("/search")
    public IPage<UserVo> search(@RequestBody UserSearchDto searchDto) {
        Page<User> page = userService.lambdaQuery().like(StrUtil.isNotBlank(searchDto.getName()), User::getName, searchDto.getName())
                .like(StrUtil.isNotBlank(searchDto.getPhone()), User::getPhone, searchDto.getPhone())
                .eq(ObjectUtil.isNotNull(searchDto.getStatus()), User::getStatus, searchDto.getStatus())
                .eq(ObjectUtil.isNotNull(searchDto.getRoleId()), User::getRoleId, searchDto.getRoleId())
                .eq(ObjectUtil.isNotNull(searchDto.getDepartmentId()), User::getDepartmentId, searchDto.getDepartmentId())
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
        if (one != null) {
            throw new ServerException("邮箱已存在");
        }
        one = userService.lambdaQuery().eq(User::getPhone, user.getPhone()).one();
        if (one != null) {
            throw new ServerException("手机号已存在");
        }
        one = userService.lambdaQuery().eq(User::getSeat, user.getSeat()).one();
        if (one != null) {
            throw new ServerException("作为已存在");
        }

        if (StrUtil.isBlank(user.getNo())) {
            user.setNo(IdUtil.getSnowflakeNextIdStr());
        }
        user.setPassword(DigestUtil.md5Hex("password"));
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

    @PostMapping("/import")
    public void importData(@RequestParam("file") MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            EasyExcel.read(inputStream, UserExcelImport.class, new PageReadListener<UserExcelImport>(data -> {
                data.forEach(user -> {
                    User map = mapperFacade.map(user, User.class);
                    map.setRoleId(roleService.lambdaQuery().eq(Role::getRoleName, user.getRoleName()).last("limit 1").one().getId());
                    map.setStatus(getStatus(user.getStatusStr()));
                    save(map);
                });
            })).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Integer getStatus(String statusStr) {
        if ("在职".equals(statusStr)) {
            return 1;
        } else if ("离职".equals(statusStr)) {
            return 0;
        } else {
            return 2;
        }
    }

    @PostMapping("/export")
    public void exportData(@RequestBody UserSearchDto searchDto, HttpServletResponse response) {
        searchDto.setPageNumber(1);
        searchDto.setPageSize(-1);
        IPage<UserVo> search = search(searchDto);
        response.reset();
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        try {
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + System.currentTimeMillis() + ".xlsx");
            EasyExcel.write(response.getOutputStream(), UserExcelExport.class).sheet().doWrite(() -> search.getRecords().stream().map(item -> {
                UserExcelExport userExcelExport = mapperFacade.map(item, UserExcelExport.class);
                userExcelExport.setRoleName(getRoleName(userExcelExport.getRoleId()));
                userExcelExport.setDepartmentName(departmentService.getById(userExcelExport.getDepartmentId()).getName());
                userExcelExport.setStatusStr(getStatusName(userExcelExport.getStatus()));
                return userExcelExport;
            }).collect(Collectors.toList()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getStatusName(Integer status) {
        if (status == 0) {
            return "离职";
        } else if (status == 1) {
            return "在职";
        } else {
            return "实习生";
        }
    }

    private String getRoleName(Integer roleId) {
        if (roleId == 1) {
            return "系统管理";
        } else if (roleId == 2) {
            return "测试人员";
        } else if (roleId == 3) {
            return "开发人员";
        } else {
            return "主管";
        }
    }
}
