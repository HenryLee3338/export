package cn.henry.service.cargo;

import cn.henry.domain.cargo.Contract;
import cn.henry.domain.cargo.Shipping;
import cn.henry.domain.cargo.ShippingExample;
import com.github.pagehelper.PageInfo;

public interface ShippingService {
    //查询所有
    PageInfo findAll(ShippingExample example, int page, int size);

    //通过id查询委托单
    Shipping findById(String id);

    //保存委托单信息
    void save(Shipping shipping);

    //更新委托单信息
    void update(Shipping shipping);

    //通过id删除委托单
    void delete(String id);
}
