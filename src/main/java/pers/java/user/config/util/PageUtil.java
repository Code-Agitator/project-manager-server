package pers.java.user.config.util;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class PageUtil {

    /**
     * @Description:转换为 IPage 对象
     * @Author: tarzan
     * @Date: 2019/10/31 9:40
     */
    public static <T, E> IPage<T> copy(IPage page, List<E> sourceList, Class<T> targetClazz, Consumer<T> consumer) {
        IPage pageResult = new Page(page.getCurrent(), page.getSize(), page.getTotal());
        pageResult.setPages(page.getPages());
        List<T> records = sourceList.stream().map(source -> BeanUtil.copyProperties(source, targetClazz)).collect(Collectors.toList());
        records.forEach(consumer);
        pageResult.setRecords(records);
        return pageResult;
    }

    /**
     * @Description:转换为 IPage 对象
     * @Author: tarzan
     * @Date: 2019/10/31 9:40
     */
    public static <T, E> IPage<T> copy(IPage page, Class<T> targetClazz, Consumer<T> consumer) {
        return copy(page, page.getRecords(), targetClazz, consumer);
    }

}
