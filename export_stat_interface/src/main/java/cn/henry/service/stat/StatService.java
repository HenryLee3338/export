package cn.henry.service.stat;

import java.util.List;
import java.util.Map;


public interface StatService {
    List<Map> getFactoryData(String companyId);
    List<Map> getOnlineData(String companyId);
    List<Map> getSellData(String companyId);

    //销售单价排行榜
    List<Map> getPriceData(String companyId);

    List<Map> getlojinipData (String companyId);
}
