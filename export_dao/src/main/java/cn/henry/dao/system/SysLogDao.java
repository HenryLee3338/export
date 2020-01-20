package cn.henry.dao.system;

import cn.henry.domain.system.SysLog;

import java.util.List;

public interface SysLogDao {
    List<SysLog> findAll(String companyId);

    void save(SysLog sysLog);
}
