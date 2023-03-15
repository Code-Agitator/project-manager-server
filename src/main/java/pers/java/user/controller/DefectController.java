package pers.java.user.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import pers.java.user.common.utils.MybatisUtils;
import pers.java.user.config.util.PageUtil;
import pers.java.user.controller.dto.DefectSearchDto;
import pers.java.user.controller.vo.DefectVo;
import pers.java.user.domain.Defect;
import pers.java.user.service.DefectService;
import pers.java.user.service.UserService;

import javax.annotation.Resource;

@RestController
@RequestMapping("/defect")
@Api(tags = "缺陷管理")
public class DefectController {
    @Resource
    DefectService defectService;

    @Resource
    UserService userService;

    @GetMapping("/search")
    public IPage<DefectVo> search(DefectSearchDto searchDto) {
        Page<Defect> page = defectService.lambdaQuery().setEntity(BeanUtil.copyProperties(searchDto, Defect.class))
                .like(StrUtil.isNotBlank(searchDto.getKeywords()), Defect::getTitle, searchDto.getKeywords())
                .page(MybatisUtils.initPage(searchDto));
        return PageUtil.copy(page, DefectVo.class, defectVo -> {
            defectVo.setUser(userService.getById(defectVo.getUserId()))
                    .setReportUser(userService.getById(defectVo.getReportUserId()));
        });
    }

    @PostMapping("/save")
    public Defect save(@RequestBody Defect defect) {
        defectService.save(defect);
        return defect;
    }

    @PutMapping("/update")
    public Defect update(@RequestBody Defect defect) {
        defectService.updateById(defect);
        return defect;
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Integer id) {
        defectService.removeById(id);
    }
}
