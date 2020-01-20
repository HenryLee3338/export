package cn.henry.service.stat.impl;

import cn.henry.dao.stat.StatDao;
import cn.henry.service.stat.StatService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@Service
public class StatServiceImpl implements StatService {

    @Autowired
    private StatDao statDao;

    public List<Map> getFactoryData(String companyId) {
        return statDao.getFactoryData(companyId);
    }

    public List<Map> getOnlineData(String companyId) {
        return statDao.getOnlineData(companyId);
    }

    public List<Map> getSellData(String companyId) {
        return statDao.getSellData(companyId);
    }

    //销售单价排行榜
    public List<Map> getPriceData(String companyId){
        return statDao.getPriceData(companyId);
    }

    public List<Map> getlojinipData(String companyId) {
        return statDao.getlojinipData(companyId);
    }
}
