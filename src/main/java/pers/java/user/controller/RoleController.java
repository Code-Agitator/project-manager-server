package pers.java.user.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import pers.java.user.common.utils.MybatisUtils;
import pers.java.user.domain.Role;
import pers.java.user.service.RoleService;

import javax.annotation.Resource;

@RestController
@RequestMapping("/role")
@Api(tags = "角色")
public class RoleController {

    @Resource
    RoleService roleService;

    @PostMapping("/save")
    public Role save(@RequestBody Role role) {
        roleService.save(role);
        return role;
    }

    @GetMapping("/search")
    public IPage<Role> search(Integer pageSize, Integer pageNumber, String roleName, String roleCode) {
        return roleService.lambdaQuery().like(StrUtil.isNotBlank(roleName), Role::getRoleName, roleName)
                .like(StrUtil.isNotBlank(roleCode), Role::getRoleCode, roleCode).page(MybatisUtils.initPage(pageNumber, pageSize));
    }

    @PutMapping("/update")
    public Role update(@RequestBody Role role) {
        roleService.updateById(role);
        return role;
    }


    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {
        roleService.removeById(id);
    }
}
