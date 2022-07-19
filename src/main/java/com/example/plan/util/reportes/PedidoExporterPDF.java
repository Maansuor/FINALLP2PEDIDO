/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.plan.util.reportes;

import com.example.plan.entity.Pedido;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author KAREN
 */
public class PedidoExporterPDF {
    private List<Pedido> listaPedidos;
    
    
    public PedidoExporterPDF(List<Pedido> listaPedidos) {
        super();
        this.listaPedidos = listaPedidos;
    }
    
    
    private void escribirCabeceraDeLaTabla(PdfPTable tabla) {
        PdfPCell celda = new PdfPCell();

        celda.setBackgroundColor(Color.RED);
        celda.setPadding(5);

        Font fuente = FontFactory.getFont(FontFactory.HELVETICA);
        fuente.setColor(Color.WHITE);

        celda.setPhrase(new Phrase("ID", fuente));
        tabla.addCell(celda);

        celda.setPhrase(new Phrase("Clientes", fuente));
        tabla.addCell(celda);

        celda.setPhrase(new Phrase("Fecha", fuente));
        tabla.addCell(celda);
        
        celda.setPhrase(new Phrase("Reparto", fuente));
        tabla.addCell(celda);
        
        celda.setPhrase(new Phrase("Producto", fuente));
        tabla.addCell(celda);
        
        celda.setPhrase(new Phrase("Cantidad", fuente));
        tabla.addCell(celda);
        
        celda.setPhrase(new Phrase("Precio", fuente));
        tabla.addCell(celda);

    }
    
    private void escribirDatosDeLaTabla(PdfPTable tabla) {
        for (Pedido pedido : listaPedidos) {
            tabla.addCell(String.valueOf(pedido.getId()));
            tabla.addCell(pedido.getCliente());
            tabla.addCell(String.valueOf(pedido.getFecha()));
            tabla.addCell(pedido.getReparto());
            tabla.addCell(pedido.getProducto());
            tabla.addCell(String.valueOf(pedido.getCantidad()));
            
            tabla.addCell(String.valueOf(pedido.getPrecio()));
            
        }
    }

    
    
    public void exportar(HttpServletResponse response) throws DocumentException, IOException {
        Document documento = new Document(PageSize.A4);
        PdfWriter.getInstance(documento, response.getOutputStream());

        documento.open();
        
        
        Font fuente = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fuente.setColor(Color.BLACK);
        fuente.setSize(18);

        Paragraph titulo = new Paragraph("Lista de Pedidos", fuente);
        titulo.setAlignment(Paragraph.ALIGN_CENTER);
        documento.add(titulo);

        PdfPTable tabla = new PdfPTable(7);
        tabla.setWidthPercentage(100);
        tabla.setSpacingBefore(15);
        tabla.setWidths(new float[]{1f, 2.3f, 2.3f, 2.3f, 2.3f, 2.3f, 5f});
        tabla.setWidthPercentage(110);

        escribirCabeceraDeLaTabla(tabla);
        escribirDatosDeLaTabla(tabla);

        documento.add(tabla);
        documento.close();
    }
    
}