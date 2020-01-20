package cn.henry.service.system.impl;

import cn.henry.dao.system.SysLogDao;
import cn.henry.domain.system.SysLog;
import cn.henry.service.system.SysLogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SysLogServiceImpl implements SysLogService {
    @Autowired
    private SysLogDao sysLogDao;
    public PageInfo findAll(String companyId, int page, int size) {
        PageHelper.startPage(page,size);
        List<SysLog> list = sysLogDao.findAll(companyId);
        return new PageInfo(list);
    }

    public void save(SysLog sysLog) {
        sysLogDao.save(sysLog);
    }
}
