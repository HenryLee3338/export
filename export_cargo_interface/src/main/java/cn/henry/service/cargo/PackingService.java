package cn.henry.service.cargo;

import cn.henry.domain.cargo.Packing;
import cn.henry.domain.cargo.PackingExample;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface PackingService {
    //查询所有
    PageInfo findAll(PackingExample example, int page, int size);
    List<Packing> findAll(PackingExample example);

    //通过id查询委托单
    Packing findById(String id);

    //保存委托单信息
    void save(Packing packing);

    //更新委托单信息
    void update(Packing packing);

    //通过id删除委托单
    void delete(String id);
}
