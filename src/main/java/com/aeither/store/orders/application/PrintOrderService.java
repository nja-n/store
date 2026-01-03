package com.aeither.store.orders.application;

import org.springframework.stereotype.Service;

import com.aeither.store.administration.domain.model.Company;
import com.aeither.store.orders.domain.model.Order;
import com.aeither.store.orders.infrastructure.services.PdfGeneratorService;

@Service
public class PrintOrderService {
    private final PdfGeneratorService pdfGenerator;
    private final CompanyClient companyClient;

    public PrintOrderService(PdfGeneratorService pdfGenerator, CompanyClient companyClient) {
        this.pdfGenerator = pdfGenerator;
        this.companyClient = companyClient;
    }

    public byte[] printOrder(Order order) {
        Long companyId = order.getCompanyId();
        Company company = companyClient.getCompany(companyId);

        return pdfGenerator.generateOrderPdf(order, company);
    }

}
