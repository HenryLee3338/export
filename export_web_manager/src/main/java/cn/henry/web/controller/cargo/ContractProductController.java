package cn.henry.web.controller.cargo;

import cn.henry.common.utils.UploadUtil;
import cn.henry.domain.cargo.*;
import cn.henry.service.cargo.ContractProductService;
import cn.henry.service.cargo.FactoryService;
import cn.henry.service.cargo.HuoHaoService;
import cn.henry.web.controller.BaseController;
import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(value = "/cargo/contractProduct")
public class ContractProductController extends BaseController{

    @Reference
    private ContractProductService contractProductService;

    @Reference
    private FactoryService factoryService;

    @Reference
    private HuoHaoService huoHaoService;

    @RequestMapping(value = "/list")
    public String list(@RequestParam(defaultValue = "1") int page,
                       @RequestParam(defaultValue = "10") int size, String contractId){

        //1. 通过contractProductService分页查询所有的货物信息
        ContractProductExample example = new ContractProductExample();
        ContractProductExample.Criteria criteria = example.createCriteria();
        criteria.andContractIdEqualTo(contractId);
        PageInfo pageInfo = contractProductService.findAll(example, page, size);
        //2. 将货物信息放入request域当中
        request.setAttribute("page", pageInfo);

        //3. 通过factoryService查询所有货物的厂家
        FactoryExample factoryExample = new FactoryExample();
        FactoryExample.Criteria factoryCriteria = factoryExample.createCriteria();
        factoryCriteria.andCtypeEqualTo("货物");
        List<Factory> factoryList = factoryService.findAll(factoryExample);
        //4. 将货物厂家信息放入request域当中
        request.setAttribute("factoryList", factoryList);

        //5. 将contractId放入到request域当中
        request.setAttribute("contractId", contractId);
        //6. 跳转货物列表页面
        return "cargo/product/product-list";
    }


    @RequestMapping(value = "/edit")
    public String edit(ContractProduct contractProduct, MultipartFile productPhoto) throws IOException {
        //contractProduct接收合同id，货物信息
        //System.out.println(contractProduct.toString());

        contractProduct.setCompanyId(companyId);
        contractProduct.setCompanyName(companyName);

        if (productPhoto != null){
            String upload = new UploadUtil().upload(productPhoto.getBytes());
            contractProduct.setProductImage(upload);
        }


        if (StringUtil.isEmpty(contractProduct.getId())){
            contractProduct.setId(UUID.randomUUID().toString());
            contractProductService.save(contractProduct);
        }else {
            contractProductService.update(contractProduct);
        }

        return "redirect:/cargo/contractProduct/list.do?contractId="+contractProduct.getContractId();
    }

    @RequestMapping(value = "/toUpdate")
    public String toUpdate(String id){
        ContractProduct contractProduct = contractProductService.findById(id);
        request.setAttribute("contractProduct", contractProduct);
        return "cargo/product/product-update";
    }

    @RequestMapping(value = "/delete")
    public String delete(String id, String contractId){
        contractProductService.delete(id);
        return "redirect:/cargo/contractProduct/list.do?contractId="+contractId;
    }

    @RequestMapping(value = "/toImport")
    public String toImport(String contractId){
        request.setAttribute("contractId", contractId);
        return "cargo/product/product-import";
    }


    @RequestMapping(value = "/import")
    public String importExcel(String contractId, MultipartFile file) throws IOException {
        //1.读取excel表数据创建工作薄，前端传入的file得到
        Workbook wb = new XSSFWorkbook(file.getInputStream());

        //2.通过工作薄读取页
        Sheet sheet = wb.getSheetAt(0);

        //3.循环到最后一行，getLastRowNum有数据的最后一行的索引
        //i=1证明从第二行开始读取，因为第一行是标题，不用写入数据库
        for (int i=1; i<=sheet.getLastRowNum(); i++) {
            //4.读取每一行数据
            Row row = sheet.getRow(i);

            ContractProduct contractProduct = new ContractProduct();

            //5.读取单元格，写入contractProduct实体类，读取9个单元格
        /*Cell cell = row.getCell(1);
            String stringCellValue = cell.getStringCellValue();
            contractProduct.setFactoryName(stringCellValue);*/

            contractProduct.setFactoryName(row.getCell(1).getStringCellValue());
            contractProduct.setProductNo(row.getCell(2).getStringCellValue());
            contractProduct.setCnumber((int) row.getCell(3).getNumericCellValue());
            contractProduct.setPackingUnit(row.getCell(4).getStringCellValue());
            contractProduct.setLoadingRate(row.getCell(5).getNumericCellValue()+"");
            contractProduct.setBoxNum((int) row.getCell(6).getNumericCellValue());
            contractProduct.setPrice(row.getCell(7).getNumericCellValue());
            contractProduct.setProductDesc(row.getCell(8).getStringCellValue());
            contractProduct.setProductRequest(row.getCell(9).getStringCellValue());

            contractProduct.setId(UUID.randomUUID().toString());
            contractProduct.setCompanyName(companyName);
            contractProduct.setCompanyId(companyId);
            contractProduct.setContractId(contractId);

            //6.调用contractProductService.save方法写入数据库
            contractProductService.save(contractProduct);
        }

        //7.跳转页面
        return "redirect:/cargo/contractProduct/list.do?contractId="+contractId;
    }

    @RequestMapping(value = "findHuohao")
    @ResponseBody
    public List<String> findHuohao(String factoryName) {
        //System.out.println(factoryName);
        HuohaoExample example = new HuohaoExample();
        HuohaoExample.Criteria criteria = example.createCriteria();
        criteria.andFactoryNameEqualTo(factoryName);
        List<Huohao> huohaoList = huoHaoService.findByFactoryName(example);
        ArrayList<String> list = new ArrayList<String>();
        for (Huohao huohao : huohaoList) {
            String huohaoName = huohao.getHuohaoName();
            list.add(huohaoName);
        }
        return list;
    }
}
