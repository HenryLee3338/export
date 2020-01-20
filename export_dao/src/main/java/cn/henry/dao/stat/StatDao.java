package cn.henry.dao.stat;

import java.util.List;
import java.util.Map;

public interface StatDao {
    List<Map> getFactoryData(String companyId);
    List<Map> getOnlineData(String companyId);
    List<Map> getSellData(String companyId);
    //销售单价排行
    List<Map> getPriceData(String companyId);
    List<Map> getlojinipData(String companyId);
}
