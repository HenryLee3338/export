package cn.henry.service.cargo;

import cn.henry.domain.cargo.Export;
import cn.henry.domain.cargo.ExportExample;
import cn.henry.domain.vo.ExportResult;
import com.github.pagehelper.PageInfo;

public interface ExportService {
    Export findById(String id);

    void save(Export export);

    void update(Export export);

    void delete(String id);

    PageInfo findAll(ExportExample example, int page, int size);

    void updateE(ExportResult exportResult);
}
