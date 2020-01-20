package cn.henry.service.cargo;

import cn.henry.dao.cargo.*;
import cn.henry.domain.cargo.*;
import cn.henry.domain.vo.ExportProductResult;
import cn.henry.domain.vo.ExportResult;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ExportServiceImpl implements ExportService {

    @Autowired
    private ExportDao exportDao;

    @Autowired
    private ExportProductDao exportProductDao;

    @Autowired
    private ExtEproductDao extEproductDao;

    @Autowired
    private ContractDao contractDao;

    @Autowired
    private ContractProductDao contractProductDao;

    @Autowired
    private ExtCproductDao extCproductDao;

    public Export findById(String id) {
        return exportDao.selectByPrimaryKey(id);
    }

    public void save(Export export) {
        //1.初始化货物数量，附件数量和出口货物列表
        int proNum = 0;
        int extNum = 0;
        ArrayList<ExportProduct> epList = new ArrayList<ExportProduct>();
        //2. 设置export的状态为0
        export.setState(0);
        //3.通过export得到contractIds，循环遍历
        String[] contractIds = export.getContractIds().split(",");
        for (String contractId : contractIds) {
            //处理合同表==================================================================================
            //4.通过contractId查询合同实体类
            Contract contract = contractDao.selectByPrimaryKey(contractId);
            //5.更新合同的状态为已报运,state=2
            contract.setState(2);
            //6.更新合同到数据库
            contractDao.updateByPrimaryKeySelective(contract);
            // 处理合同货物表==========================================================================
            //7.通过contractId查询合同货物列表
            ContractProductExample contractProductExample = new ContractProductExample();
            ContractProductExample.Criteria criteria = contractProductExample.createCriteria();
            criteria.andContractIdEqualTo(contractId);
            List<ContractProduct> cpList = contractProductDao.selectByExample(contractProductExample);
            //8.循环合同货物列表，得到合同货物contractProduct
            for (ContractProduct contractProduct : cpList) {
                //9.创建报运商品实体类ExportProduct
                ExportProduct exportProduct = new ExportProduct();
                //10.将合同信息写入到报运商品信息
                BeanUtils.copyProperties(contractProduct,exportProduct);
                //11.设置报运商品的报运单exportId
                exportProduct.setExportId(export.getId());
                //12.设置报运商品的id
                exportProduct.setId(UUID.randomUUID().toString());
                //13.将报运商品写入到数据库
                exportProductDao.insertSelective(exportProduct);

                proNum++;//货物数量加一

                //14.将报运商品列表设置到报运单当中
                epList.add(exportProduct);
                //处理附件表===============================================================================
                //15.通过contractProductId查询合同附件列表
                ExtCproductExample extCproductExample = new ExtCproductExample();
                ExtCproductExample.Criteria extCriteria = extCproductExample.createCriteria();
                extCriteria.andContractProductIdEqualTo(contractProduct.getId());
                List<ExtCproduct> extCList = extCproductDao.selectByExample(extCproductExample);
                //16.循环合同附件列表，得到合同附件extCproduct
                for (ExtCproduct extCproduct : extCList) {
                    //17.创建报运附件实体类
                    ExtEproduct extEproduct = new ExtEproduct();
                    //18.将合同附件写入到报运附件信息
                    BeanUtils.copyProperties(extCproduct,extEproduct);
                    //19.设置报运附件的报运单Id
                    extEproduct.setExportId(export.getId());
                    //20.设置报运单的报运商品Id
                    extEproduct.setExportProductId(exportProduct.getId());
                    //21.设置报运附件的id
                    extEproduct.setId(UUID.randomUUID().toString());
                    //22.将报运附件写入数据库
                    extEproductDao.insertSelective(extEproduct);

                    extNum++;//附件数量加一
                }
            }
            //23、合计报运商品数量设置到报运单中
            export.setProNum(proNum);
            //24、合计报运附件数量设置到报运单中
            export.setExtNum(extNum);
            //25、设置报运单商品列表
            export.setExportProducts(epList);
            //26、将报运单写入数据库
            exportDao.insertSelective(export);
        }
    }

    public void update(Export export) {
        exportDao.updateByPrimaryKeySelective(export);
    }

    public void delete(String id) {
        exportDao.deleteByPrimaryKey(id);
    }

    public PageInfo findAll(ExportExample example, int page, int size) {
        PageHelper.startPage(page, size);
        List<Export> list = exportDao.selectByExample(example);
        return new PageInfo(list);
    }

    public void updateE(ExportResult exportResult) {
        //1.根据exportResult更新export实体类
        Export export = new Export();
        export.setId(exportResult.getExportId());
        export.setState(exportResult.getState());//设置export的state为2
        export.setRemark(exportResult.getRemark());
        //2.将export更新到数据库
        exportDao.updateByPrimaryKeySelective(export);
        //3.根据exportResult.products循环
        for (ExportProductResult productResult : exportResult.getProducts()) {
            //4.更新exportProduct实体类
            ExportProduct exportProduct = new ExportProduct();
            exportProduct.setId(productResult.getExportProductId());
            exportProduct.setTax(productResult.getTax());
            //5.将exportProduct更新到数据库
            exportProductDao.updateByPrimaryKeySelective(exportProduct);
        }
    }
}
