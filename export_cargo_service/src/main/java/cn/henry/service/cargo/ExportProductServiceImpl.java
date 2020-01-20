package cn.henry.service.cargo;

import cn.henry.dao.cargo.ExportProductDao;
import cn.henry.domain.cargo.ExportProduct;
import cn.henry.domain.cargo.ExportProductExample;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class ExportProductServiceImpl implements ExportProductService{

    @Autowired
    private ExportProductDao exportProductDao;

    public List<ExportProduct> findAll(ExportProductExample example) {
        return exportProductDao.selectByExample(example);
    }
}
