package cn.henry.web.controller.cargo;

import cn.henry.domain.cargo.Export;
import cn.henry.domain.cargo.ExportExample;
import cn.henry.domain.cargo.Packing;
import cn.henry.domain.cargo.PackingExample;
import cn.henry.service.cargo.ExportService;
import cn.henry.service.cargo.PackingService;
import cn.henry.web.controller.BaseController;
import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/cargo/packing")
public class PackingController extends BaseController {
    @Reference
    private ExportService exportService;

    @Reference
    private PackingService packingService;

    @RequestMapping(value = "/list")
    public String list(@RequestParam(defaultValue = "1") int page,
                       @RequestParam(defaultValue = "10") int size){
        ExportExample exportExample = new ExportExample();
        ExportExample.Criteria criteria = exportExample.createCriteria();
        criteria.andCompanyIdEqualTo(companyId);
//        criteria.andStateEqualTo(2L);
        List<Long> list = new ArrayList<Long>();
        list.add(2L);
        list.add(3L);
        list.add(4L);
        criteria.andStateIn(list);
        PageInfo pageInfo = exportService.findAll(exportExample, page, size);
        request.setAttribute("page", pageInfo);
        return "cargo/packing/packing-list";
    }

    @RequestMapping(value = "toPack")
    public String toPack(String[] idAndState){
        if(idAndState[1].length()==1){
            System.out.println("hh==================");
            System.out.println(idAndState[1]);
            if("3".equals(idAndState[1]) || "4".equals(idAndState[1])){
                request.setAttribute("error","当前状态无法装箱");
                return "forward:/cargo/packing/list.do";
            }else{
                request.setAttribute("ids",idAndState[0]);
                request.removeAttribute("error");
                return "cargo/packing/packing-toPack";
            }
        }else{
            for (String s : idAndState) {
                System.out.println("--------------------------");
                System.out.println("--------------------------");
                System.out.println("--------------------------");
                System.out.println(s);
            }
            System.out.println(idAndState.length);
            Boolean bool = true;
            String strids="";
            String[] ids = new String[idAndState.length+2];
            for (int i = 0; i < idAndState.length; i++) {
                String[] arr = idAndState[i].split(",");
                System.out.println(arr[1]);
                int state = Integer.parseInt(arr[1]);
                if(state>=3){
                    bool = false;
                    break;
                }
                ids[i] = arr[0];
                strids+=arr[0]+",";
            }

            strids.substring(0,strids.length()-1);
            if(bool){
                //更据出发地和目的地判断是否能装箱
                //切割id
                //取出第一个id的报运单
                Export oneExport = exportService.findById(ids[0]);
                //
                Boolean bool1= true;
                if(ids.length==1){
                    request.setAttribute("ids",ids[0]);
                    request.removeAttribute("error");
                    return "cargo/packing/packing-toPack";
                }else{
                    //遍历ids看看是否能取出
                    for (int i = 1; i < ids.length; i++) {
                        Export export = exportService.findById(ids[i]);
                        if(!oneExport.getShipmentPort().equals(export.getShipmentPort())
                                &&!oneExport.getDestinationPort().equals(export.getDestinationPort())){
                            bool=false;
                        }
                        break;
                    }
                }
                //判断是否能装箱
                if(bool){
                    request.setAttribute("ids",strids);
                    request.removeAttribute("error");
                    return "cargo/packing/packing-toPack";
                }else{
                    request.setAttribute("error","出发地和目的地不一致，请检查并重新装箱");
                    return "forward:/cargo/packing/list.do";
                }
            }else{
                request.setAttribute("error1","当前状态不能装箱");
                return "cargo/packing/list.do";
            }

        /*StringBuilder sb = new StringBuilder();
        for (String s : idAndState) {
            String[] split = s.split(",");
            String id = split[0];
            sb.append(id);
            sb.append(",");
        }
        String ids = sb.toString();
        String ids1 = ids.substring(0, ids.length() - 1);
        System.out.println(ids1);
        request.setAttribute("id",ids1);
        return "cargo/packing/packing-toPack";*/
        }


    }

    //    ======================================================保存或者更新packing表========================================
    //    ==================================================改变export.state=3, 在前端显示已经装箱===========================
    @RequestMapping(value = "/edit")
    public String edit(Packing packing){
        /*if (StringUtil.isEmpty(packing.getPackingListId())){//新创建的packing对象
            packing.setPackingListId(UUID.randomUUID().toString());
            packing.setState(1);//设置packing的状态改为1，以后在委托页面state=1显示为草稿, 2显示为已委托
            packing.setCompanyId(companyId);
            packing.setCompanyName(companyName);
            packing.setCreateTime(new Date());
            packing.setCreateBy(user.getUserName());
            packing.setCreateDept(user.getDeptName());
            packingService.save(packing);

        }else {//在委托页面更新packing对象

        }
        return "redirect:/cargo/packing/list.do";*/
        String uuid = UUID.randomUUID().toString();
        packing.setPackingListId(uuid);
        packing.setCompanyId(companyId);
        packing.setCompanyName(companyName);
        packing.setState(1);
        packingService.save(packing);
        String exportIds = packing.getExportIds();
        String[] ids = exportIds.split(",");

        for (String id : ids){
            System.out.println("--------");
            System.out.println(id);
            Export export = exportService.findById(id);
            System.out.println(export);
            System.out.println("--------");
            export.setState(3);
            exportService.update(export);
        }
        request.setAttribute("packingId","11");
        return "redirect:/cargo/packing/list.do";
    }

    //取消export的装箱状态,将export的状态由已装箱改为草稿3=>2，删除装箱列表中的实例
    @RequestMapping("cancel")
    public String cancel(String id){
        Export export = exportService.findById(id);
        //拿到装箱表实体类
        PackingExample example = new PackingExample();
        PackingExample.Criteria criteria = example.createCriteria();
        criteria.andCompanyIdEqualTo(companyId);
        List<Packing> list = packingService.findAll(example);
        Packing packing = null;
        for (Packing p : list) {
            if(p.getExportIds().contains(id)){
                packing = p;
            }
        }
        //切割ids做修改
        String ids = packing.getExportIds();
        String[] arr = ids.split(",");
        String resultId = "";
        if(arr.length==1){
            packingService.delete(packing.getPackingListId());
            //改变状态
            export.setState(2);
            exportService.update(export);
            return "redirect:/cargo/packing/list.do";
        }else{
            for (int i = 0; i <arr.length ; i++) {
                if(!arr[i].equals(id)){
                    resultId += (arr[i]+",");
                }
            }
        }
        resultId = resultId.substring(0, resultId.length()-1);
        System.out.println(resultId);
        //改变报运单状态
        export.setState(2);
        exportService.update(export);
        //更新装箱表ids
        packing.setExportIds(resultId);
        packingService.update(packing);
        return "redirect:/cargo/packing/list.do";
    }

    @RequestMapping("toList")
    public String toList(){
        return "redirect:/cargo/packing/list.do";
    }
}
