package cn.henry.service.cargo;

import cn.henry.domain.cargo.ExtCproduct;
import cn.henry.domain.cargo.ExtCproductExample;
import com.github.pagehelper.PageInfo;

public interface ExtCproductService {

    //分页查询所有附件
    PageInfo findAll(ExtCproductExample example, int page, int size);

    //通过id查询所有的附件
    ExtCproduct findById(String id);

    //保存附件信息
    void save(ExtCproduct extCproduct);

    //更新附件信息
    void update(ExtCproduct extCproduct);

    //删除附件信息
    void delete(String id);
}