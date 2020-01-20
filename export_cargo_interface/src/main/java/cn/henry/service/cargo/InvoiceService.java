package cn.henry.service.cargo;

import cn.henry.domain.cargo.Invoice;
import cn.henry.domain.cargo.InvoiceExample;
import com.github.pagehelper.PageInfo;

public interface InvoiceService {
    PageInfo findAll(InvoiceExample example, int page, int size);

    void delete(String invoiceId);

     void  update(Invoice invoice);

    Invoice findById(String invoiceId);

    void save(Invoice invoice);
}
