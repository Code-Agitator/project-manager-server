package pers.java.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import pers.java.user.mapper.DefectMapper;
import pers.java.user.service.DefectService;
import pers.java.user.domain.Defect;
import org.springframework.stereotype.Service;

/**
* @author 14820
* @description 针对表【t_defect(缺陷表)】的数据库操作Service实现
* @createDate 2023-03-15 17:42:39
*/
@Service
public class DefectServiceImpl extends ServiceImpl<DefectMapper, Defect>
    implements DefectService {

}




