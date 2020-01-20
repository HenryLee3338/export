package cn.henry.web.controller.cargo;

import cn.henry.common.utils.BeanMapUtil;
import cn.henry.domain.cargo.Export;
import cn.henry.domain.cargo.ExportProduct;
import cn.henry.domain.cargo.ExportProductExample;
import cn.henry.service.cargo.ExportProductService;
import cn.henry.service.cargo.ExportService;
import cn.henry.web.controller.BaseController;
import com.alibaba.dubbo.config.annotation.Reference;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
@RequestMapping(value = "/cargo/export")
public class PdfController extends BaseController{

    @Reference
    private ExportService exportService;

    @Reference
    private ExportProductService exportProductService;

    @RequestMapping(value = "/exportPdf")
    public void exportPdf(String id) throws Exception {
        String path = "E:\\IdeaProjects\\export_parent\\export_web_manager\\src\\main\\webapp\\jasper\\export.jasper";

        //通过exportService查找export实体类
        Export export = exportService.findById(id);

        export.setCustomerContract("heima118_1");
        //将实体类转化为Map
        /*Map map = new HashMap();
        map.put("id", export.getId());*/
        Map<String, Object> map = BeanMapUtil.beanToMap(export);


        //通过exportProductService查找exportProduct列表
        ExportProductExample example = new ExportProductExample();
        ExportProductExample.Criteria criteria = example.createCriteria();
        criteria.andExportIdEqualTo(id);
        List<ExportProduct> list = exportProductService.findAll(example);

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);

        //map： export报运单主数据
        //list: exportProduct报运单商品数据
        JasperPrint jasperPrint = JasperFillManager.fillReport(path, map, dataSource);
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
    }

    @RequestMapping(value = "/exportPdf06")
    public void exportPdf06() throws Exception {
        String path = "D:\\02_lessons\\JAVAEE_118_Class\\day13\\01-Code\\export_parent\\export_web_manager\\src\\main\\webapp\\jasper\\test06.jasper";

        List<Map> list = new ArrayList<Map>();
        for (int i = 0; i < 6; i++) {
            Map map = new HashMap();
            map.put("name", "菜"+i+"0");
            map.put("value", new Random().nextInt(1000));
            list.add(map);
        }

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);

        JasperPrint jasperPrint = JasperFillManager.fillReport(path, new HashMap(), dataSource);
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
    }

    @RequestMapping(value = "/exportPdf05")
    public void exportPdf05() throws Exception {
        String path = "D:\\02_lessons\\JAVAEE_118_Class\\day13\\01-Code\\export_parent\\export_web_manager\\src\\main\\webapp\\jasper\\test05.jasper";

        List<Map> list = new ArrayList<Map>();
        for (int i = 0; i < 6; i++) {
            Map map = new HashMap();
            map.put("username", "菜"+i+"0");
            map.put("age", 30);
            map.put("deptname", "开发部");
            map.put("companyname", "传智播客");
            list.add(map);
        }

        for (int i = 0; i < 4; i++) {
            Map map = new HashMap();
            map.put("username", "菜"+i+"0");
            map.put("age", 40);
            map.put("deptname", "测试部");
            map.put("companyname", "传智播客");
            list.add(map);
        }

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);

        JasperPrint jasperPrint = JasperFillManager.fillReport(path, new HashMap(), dataSource);
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
    }

    @RequestMapping(value = "/exportPdf04")
    public void exportPdf04() throws Exception {
        String path = "D:\\02_lessons\\JAVAEE_118_Class\\day13\\01-Code\\export_parent\\export_web_manager\\src\\main\\webapp\\jasper\\test04.jasper";

        List<Map> list = new ArrayList<Map>();

        for (int i = 0; i < 6; i++) {
            Map map = new HashMap();
            map.put("username", "菜"+i+"0");
            map.put("age", 30);
            map.put("deptname", "开发部");
            map.put("companyname", "传智播客");
            list.add(map);
        }

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);

        JasperPrint jasperPrint = JasperFillManager.fillReport(path, new HashMap(), dataSource);
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
    }


    @RequestMapping(value = "/exportPdf03")
    public void exportPdf03() throws Exception {
        String path = "D:\\02_lessons\\JAVAEE_118_Class\\day13\\01-Code\\export_parent\\export_web_manager\\src\\main\\webapp\\jasper\\test03.jasper";

        Map map = new HashMap();
        map.put("username", "菜10");
        map.put("age", 30);
        map.put("deptname", "开发部");
        map.put("companyname", "传智播客");

        JasperPrint jasperPrint = JasperFillManager.fillReport(path, map, new JREmptyDataSource());
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
    }


    @RequestMapping(value = "/exportPdf02")
    public void exportPdf02() throws Exception {
        String path = "D:\\02_lessons\\JAVAEE_118_Class\\day13\\01-Code\\export_parent\\export_web_manager\\src\\main\\webapp\\jasper\\test02.jasper";
        JasperPrint jasperPrint = JasperFillManager.fillReport(path, new HashMap(), new JREmptyDataSource());
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
    }

    @RequestMapping(value = "/exportPdf01")
    public void exportPdf01() throws Exception {
        //1.设置路径
        String path = "D:\\02_lessons\\JAVAEE_118_Class\\day13\\01-Code\\export_parent\\export_web_manager\\src\\main\\webapp\\jasper\\test01.jasper";
        //2.通过路径创建jrprint
        //  JasperFillManager：将*.jasper里的数据进行一个填充
        //                     map，填充jasper里的数据
        //                     JRDataSource，填充jasper里的表格
        JasperPrint jasperPrint = JasperFillManager.fillReport(path, new HashMap(), new JREmptyDataSource());
        //3.通过jrprint进行输出pdf
        //  JasperExportManager：将填充好的pdf转化为jasperPrint，将jasperPrint进行输出到前端页面
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
    }
}
