package pers.java.user.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import pers.java.user.common.utils.MybatisUtils;
import pers.java.user.config.util.PageUtil;
import pers.java.user.controller.dto.TestingCaseSearchDto;
import pers.java.user.controller.vo.TestingCaseVo;
import pers.java.user.domain.TestingCase;
import pers.java.user.service.TestingCaseService;
import pers.java.user.service.TestingPlanService;
import pers.java.user.service.UserService;

import javax.annotation.Resource;

@RestController
@RequestMapping("/testing/case")
public class TestingCaseController {

    @Resource
    TestingCaseService testingCaseService;
    @Resource
    UserService userService;
    @Resource
    TestingPlanService testingPlanService;

    @PostMapping("/save")
    public TestingCase save(@RequestBody TestingCase testingCase) {
        testingCaseService.save(testingCase);
        return testingCase;
    }

    @PutMapping("/update")
    public TestingCase update(@RequestBody TestingCase testingCase) {
        testingCaseService.updateById(testingCase);
        return testingCase;
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {
        testingCaseService.removeById(id);
    }

    @PostMapping("/search")
    public IPage<TestingCaseVo> search(@RequestBody TestingCaseSearchDto searchDto) {
        Page<TestingCase> page = testingCaseService.lambdaQuery().eq(ObjectUtil.isNotNull(searchDto.getUserId()), TestingCase::getUserId, searchDto.getUserId())
                .eq(ObjectUtil.isNotNull(searchDto.getPlantId()), TestingCase::getPlantId, searchDto.getPlantId())
                .like(StrUtil.isNotBlank(searchDto.getName()), TestingCase::getName, searchDto.getName()).page(MybatisUtils.initPage(searchDto));
        return PageUtil.copy(page, TestingCaseVo.class, testingCaseVo -> {
            testingCaseVo.setUser(userService.getById(testingCaseVo.getUserId()))
                    .setTestingPlan(testingPlanService.getById(testingCaseVo.getPlantId()));
        });
    }
}
