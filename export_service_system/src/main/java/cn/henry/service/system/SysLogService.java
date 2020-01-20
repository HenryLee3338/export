package cn.henry.service.system;

import cn.henry.domain.system.SysLog;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;


public interface SysLogService {
    PageInfo findAll(String companyId,int page,int size);

    void save(SysLog sysLog);
}
