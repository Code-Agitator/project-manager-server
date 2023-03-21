package pers.java.user.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import pers.java.user.common.utils.MybatisUtils;
import pers.java.user.config.util.PageUtil;
import pers.java.user.controller.vo.DepartmentVo;
import pers.java.user.domain.Department;
import pers.java.user.domain.User;
import pers.java.user.service.DepartmentService;
import pers.java.user.service.UserService;

import javax.annotation.Resource;

@RestController
@RequestMapping("/department")
@Api(tags = "部门")
public class DepartmentController {
    @Resource
    DepartmentService departmentService;

    @Resource
    UserService userService;

    @GetMapping("/search")
    public IPage<DepartmentVo> search(String keywords, Integer pageSize, Integer pageNumber) {
        Page<Department> page = departmentService.lambdaQuery().like(StrUtil.isNotBlank(keywords), Department::getName, keywords)
                .page(MybatisUtils.initPage(pageNumber, pageSize));
        return PageUtil.copy(page, DepartmentVo.class, departmentVo -> {
            departmentVo.setUser(userService.getById(departmentVo.getUserId()));
        });
    }

    @PostMapping("/save")
    public Department save(@RequestBody Department department) {
        userService.lambdaUpdate().eq(User::getId, department.getUserId()).set(User::getRoleId, 4).update();
        departmentService.save(department);
        return department;
    }

    @PutMapping("/update")
    public Department update(@RequestBody Department department) {
        userService.lambdaUpdate().eq(User::getId, departmentService.getById(department.getId()).getUserId()).ne(User::getRoleId, 1).set(User::getRoleId, 2).update();
        userService.lambdaUpdate().eq(User::getId, department.getUserId()).set(User::getRoleId, 4).update();
        departmentService.updateById(department);
        return department;
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {
        departmentService.removeById(id);
    }

}
