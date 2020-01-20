package cn.henry.web;

import cn.henry.domain.company.Company;
import cn.henry.service.company.CompanyService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

@Controller
public class ApplyController {

    @Reference
    private CompanyService companyService;

    @RequestMapping("/apply")
    @ResponseBody
    public String apply(Company company){
        try {
            System.out.println(company);

            company.setId(UUID.randomUUID().toString());
            company.setState(0);

            companyService.save(company);
            return "1";
        }catch (Exception e){
            return "2";
        }
    }
}
