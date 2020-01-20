package cn.henry.service.cargo;

import cn.henry.dao.cargo.HuohaoDao;
import cn.henry.domain.cargo.Huohao;
import cn.henry.domain.cargo.HuohaoExample;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class HuoHaoServiceImpl implements HuoHaoService {

    @Autowired
    private HuohaoDao huohaoDao;

    public PageInfo findAll(HuohaoExample example, int page, int size) {
        PageHelper.startPage(page,size);
        List<Huohao> list = huohaoDao.selectByExample(example);
        return new PageInfo(list);
    }

    public Huohao findById(String id) {
        return huohaoDao.selectByPrimaryKey(id);
    }

    public void save(Huohao huohao) {
        huohaoDao.insertSelective(huohao);
    }

    public void update(Huohao huohao) {
        huohaoDao.updateByPrimaryKeySelective(huohao);
    }

    public void delete(String id) {
        huohaoDao.deleteByPrimaryKey(id);
    }

    public List<Huohao> findByFactoryName(HuohaoExample example) {
       return huohaoDao.selectByExample(example);
    }


}
