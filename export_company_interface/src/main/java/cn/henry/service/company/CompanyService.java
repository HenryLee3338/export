package cn.henry.service.company;

import cn.henry.domain.company.Company;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface CompanyService {
    List<Company> findAll();

    void save(Company company);

    Company findById(String id);

    void update(Company company);

    void delete(String id);

    PageInfo findByPageHelper(int page, int size);
}
