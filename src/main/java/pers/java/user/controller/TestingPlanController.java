package pers.java.user.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import pers.java.user.advice.exception.children.ServerException;
import pers.java.user.common.utils.MybatisUtils;
import pers.java.user.config.util.PageUtil;
import pers.java.user.controller.dto.TestingPlanSearchDto;
import pers.java.user.controller.vo.TestingPlanVo;
import pers.java.user.domain.TestingPlan;
import pers.java.user.service.TestingPlanService;
import pers.java.user.service.UserService;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/testing/plan")
@Api(tags = "测试计划")
public class TestingPlanController {
    @Resource
    TestingPlanService testingPlanService;
    @Resource
    UserService userService;

    @Value("${spring.web.resources.static-locations}")
    String[] upload;


    @PostMapping("/search")
    public IPage<TestingPlanVo> search(@RequestBody TestingPlanSearchDto searchDto) {
        Page<TestingPlan> page = testingPlanService.lambdaQuery().like(StrUtil.isNotBlank(searchDto.getName()), TestingPlan::getName, searchDto.getName())
                .ge(ObjectUtil.isNotNull(searchDto.getStartTime()), TestingPlan::getCreateTime, searchDto.getStartTime())
                .le(ObjectUtil.isNotNull(searchDto.getEndTime()), TestingPlan::getCreateTime, searchDto.getEndTime()).page(MybatisUtils.initPage(searchDto));
        return PageUtil.copy(page, TestingPlanVo.class, testingPlanVo -> {
            testingPlanVo.setUser(userService.getById(testingPlanVo.getUserId()));
        });
    }


    @PostMapping("/save")
    public TestingPlan save(@RequestBody TestingPlan testingPlan) {
        testingPlanService.save(testingPlan);
        return testingPlan;
    }

    @PutMapping("/update")
    public TestingPlan update(@RequestBody TestingPlan testingPlan) {
        testingPlanService.save(testingPlan);
        return testingPlan;
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) {
        String dir = upload[0];
        if (file.isEmpty()) {
            throw new ServerException("文件为空");
        }
        try {
            byte[] bytes = file.getBytes();
            String fileName = System.currentTimeMillis() + file.getOriginalFilename();
            Path path = Paths.get(dir + fileName);
            Files.write(path, bytes);
            return fileName;
        } catch (IOException e) {
            throw new ServerException("上传失败");
        }
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {
        testingPlanService.removeById(id);
    }
}
