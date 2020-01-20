package cn.henry.service.cargo;


import cn.henry.domain.cargo.ExportProduct;
import cn.henry.domain.cargo.ExportProductExample;
import com.github.pagehelper.PageInfo;

import java.util.List;


public interface ExportProductService {

	//根据条件查询
	List<ExportProduct> findAll(ExportProductExample example);
}
