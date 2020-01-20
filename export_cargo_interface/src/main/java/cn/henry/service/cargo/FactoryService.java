package cn.henry.service.cargo;

import java.util.List;
import cn.henry.domain.cargo.Factory;
import cn.henry.domain.cargo.FactoryExample;
import com.github.pagehelper.PageInfo;

public interface FactoryService {
    List<Factory> findAll(FactoryExample example);

    PageInfo findAllfenye(FactoryExample example, int page, int size);

    void save(Factory factory);

    void update(Factory factory);

    Factory findById(String id);

    void delete(String id);

//    Factory findByName(String factoryName);
}
