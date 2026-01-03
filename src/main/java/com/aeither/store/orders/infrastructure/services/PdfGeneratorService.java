package com.aeither.store.orders.infrastructure.services;

import org.springframework.stereotype.Service;

import com.aeither.store.administration.domain.model.Company;
import com.aeither.store.administration.domain.model.GstPercentage;
import com.aeither.store.administration.domain.model.Store;
import com.aeither.store.orders.domain.model.Order;
import com.aeither.store.orders.domain.model.OrderItem;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;

@Service
public class PdfGeneratorService {
    
    public byte[] generateOrderPdf(Order order, Company company) {
        Store store = order.getStore();
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

            PdfWriter writer = new PdfWriter(baos);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            PdfFont bold = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
            PdfFont normal = PdfFontFactory.createFont(StandardFonts.HELVETICA);

            document.setMargins(20, 20, 20, 20);

            document.add(new Paragraph("Invoice").setFont(bold).setFontSize(16));

            /* =========================
               HEADER TABLE (2 COLUMNS)
               ========================= */
            Table header = new Table(UnitValue.createPercentArray(new float[]{70, 30}))
                    .useAllAvailableWidth();

            // LEFT – SELLER DETAILS
            Cell sellerCell = new Cell().add(
                    new Paragraph(company.getName()).setFont(bold).setFontSize(14)
                            .add("\n" + company.getAddress() + "\n")
                            .add("GSTIN: " + company.getGstin() + "\n")
                            .add("Mobile: " + company.getPhone() + "\n")
                            .add("Email: " + company.getEmail())
            );
            header.addCell(sellerCell);

            // RIGHT – INVOICE META
            Cell invoiceCell = new Cell()
                    .add(new Paragraph("Invoice No:\n" + order.getOrderNumber()).setFont(bold))
                    .add(new Paragraph("\nInvoice Date:\n" + order.getDate().toString()))
                    .add(new Paragraph("\nDue Date:\n" + order.getDueDate().toString()))
                    .setTextAlignment(TextAlignment.LEFT);
            header.addCell(invoiceCell);

            document.add(header);

            /* =========================
               BILL TO / SHIP TO
               ========================= */
            Table addressTable = new Table(UnitValue.createPercentArray(new float[]{50, 50}))
                    .useAllAvailableWidth()
                    .setMarginTop(10);

            addressTable.addCell(new Cell().add(
                    new Paragraph("BILL TO").setFont(bold)
                            .add("\n" + store.getName())
                            .add("\n" + store.getAddress())
                            .add("\nPlace of Supply: Kerala")
                            .add("\nMobile: " + store.getMobileNumber())
            ));

            addressTable.addCell(new Cell().add(
                    new Paragraph("SHIP TO").setFont(bold)
                            .add("\n" + company.getName())
                            .add("\n" + company.getAddress())
                            .add("\nMobile: " + company.getPhone())
            ));

            document.add(addressTable);

            /* =========================
               ITEMS TABLE
               ========================= */
            Table itemTable = new Table(UnitValue.createPercentArray(
                    new float[]{5, 25, 15, 8, 10, 10, 12, 12, 13}))
                    .useAllAvailableWidth()
                    .setMarginTop(15);

            String[] headers = {
                    "S.No", "Items", "HSN", "MRP", "Qty",
                    "Rate", "SGST", "CGST", "Amount"
            };

            for (String h : headers) {
                itemTable.addHeaderCell(
                        new Cell().add(new Paragraph(h).setFont(bold))
                                .setTextAlignment(TextAlignment.CENTER)
                );
            }

            // ITEMS
            int count = 1;
            for (OrderItem item : order.getItems()) {
                double cgst = item.getAsset().getWholesalePrice() * item.getQuantity() * GstPercentage.CGST / 100;
                double sgst = item.getAsset().getWholesalePrice() * item.getQuantity() * GstPercentage.SGST / 100;
                itemTable.addCell(String.valueOf(count++));
                itemTable.addCell(item.getAsset().getName());
                itemTable.addCell(item.getAsset().getHsn());
                itemTable.addCell(item.getAsset().getMrp().toString());
                itemTable.addCell(item.getQuantity().toString());
                itemTable.addCell(item.getAsset().getWholesalePrice().toString());
                itemTable.addCell(String.format("₹ %.2f (9%)", cgst));
                itemTable.addCell(String.format("₹ %.2f (9%)", sgst));
                itemTable.addCell(String.format("₹ %.2f", item.getAsset().getWholesalePrice() * item.getQuantity() + cgst + sgst));
            }

            // TOTAL ROW
            itemTable.addCell(new Cell(1, 4).add(new Paragraph("TOTAL").setFont(bold)));
            itemTable.addCell("32");
            itemTable.addCell("");
            itemTable.addCell("₹ 122.03");
            itemTable.addCell("₹ 122.03");
            itemTable.addCell("₹ 1,600");

            document.add(itemTable);

            /* =========================
               RECEIVED AMOUNT
               ========================= */
            document.add(new Paragraph("\nReceived Amount: ₹ 0").setFont(bold));

            /* =========================
               BANK + TERMS
               ========================= */
            Table footerTable = new Table(UnitValue.createPercentArray(new float[]{50, 50}))
                    .useAllAvailableWidth()
                    .setMarginTop(10);

            footerTable.addCell(new Cell().add(
                    new Paragraph("Bank Details").setFont(bold)
                            .add("\nName: D S TRADERS")
                            .add("\nIFSC: MAHB0000379")
                            .add("\nAccount No: 60503552839")
                            .add("\nBank: Bank of Maharashtra, ERNAKULAM Cochin")
            ));

            footerTable.addCell(new Cell().add(
                    new Paragraph("Terms and Conditions").setFont(bold)
                            .add("\n1. Goods once sold will not be taken back or exchanged.")
                            .add("\n2. All disputes are subject to jurisdiction only.")
            ));

            document.add(footerTable);

            document.close();
            return baos.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("PDF generation failed", e);
        }

    }
}
