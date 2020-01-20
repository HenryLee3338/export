package cn.henry.service.cargo;

import cn.henry.domain.cargo.Huohao;
import cn.henry.domain.cargo.HuohaoExample;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface HuoHaoService {

    //分页查询所有
    PageInfo findAll(HuohaoExample example, int page, int size);

    //通过id查询
    Huohao findById(String id);

    //新增财务单
    void save(Huohao huohao);

    //修改财务单
    void update(Huohao huohao);

    //删除财务单
    void delete(String id);

    List<Huohao> findByFactoryName(HuohaoExample example);
}
