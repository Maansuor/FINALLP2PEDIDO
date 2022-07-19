/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.plan.util.reportes;

import com.example.plan.entity.Pedido;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author KAREN
 */
public class PedidoExporterExcel {
    private XSSFWorkbook pedido;
    private XSSFSheet hoja; 
    
    private List<Pedido> listaPedidos; 
    
    public PedidoExporterExcel(List<Pedido> listaPedidos) {
        this.listaPedidos = listaPedidos;
        pedido = new XSSFWorkbook();
        hoja = pedido.createSheet("Pedidos");
    }
    private void escribirCabeceraDeTabla() {
        Row fila = hoja.createRow(0);

        CellStyle estilo = pedido.createCellStyle();
        XSSFFont fuente = pedido.createFont();
        fuente.setBold(true);
        fuente.setFontHeight(16);
        estilo.setFont(fuente);

        Cell celda = fila.createCell(0);
        celda.setCellValue("ID");
        celda.setCellStyle(estilo);

        celda = fila.createCell(1);
        celda.setCellValue("Clientes");
        celda.setCellStyle(estilo);

        celda = fila.createCell(2);
        celda.setCellValue("Fecha");
        celda.setCellStyle(estilo);
        
        celda = fila.createCell(3);
        celda.setCellValue("Reparto");
        celda.setCellStyle(estilo);

        celda = fila.createCell(4);
        celda.setCellValue("Producto");
        celda.setCellStyle(estilo);
        
        celda = fila.createCell(5);
        celda.setCellValue("Cantidad");
        celda.setCellStyle(estilo);
        
        celda = fila.createCell(6);
        celda.setCellValue("Precio");
        celda.setCellStyle(estilo);
    }
    
        private void escribirDatosDeLaTabla() {
        int nueroFilas = 1;

        CellStyle estilo = pedido.createCellStyle();
        XSSFFont fuente = pedido.createFont();
        fuente.setFontHeight(14);
        estilo.setFont(fuente);

        for (Pedido lib : listaPedidos) {
            Row fila = hoja.createRow(nueroFilas++);

            Cell celda = fila.createCell(0);
            celda.setCellValue(lib.getId());
            hoja.autoSizeColumn(0);
            celda.setCellStyle(estilo);

            celda = fila.createCell(1);
            celda.setCellValue(lib.getCliente());
            hoja.autoSizeColumn(1);
            celda.setCellStyle(estilo);

            celda = fila.createCell(2);
            celda.setCellValue(lib.getFecha());
            hoja.autoSizeColumn(2);
            celda.setCellStyle(estilo);
            
            celda = fila.createCell(3);
            celda.setCellValue(lib.getReparto());
            hoja.autoSizeColumn(3);
            celda.setCellStyle(estilo);
            
            celda = fila.createCell(4);
            celda.setCellValue(lib.getProducto());
            hoja.autoSizeColumn(4);
            celda.setCellStyle(estilo);
            
            celda = fila.createCell(5);
            celda.setCellValue(lib.getCantidad());
            hoja.autoSizeColumn(5);
            celda.setCellStyle(estilo);
            
            celda = fila.createCell(6);
            celda.setCellValue(lib.getPrecio());
            hoja.autoSizeColumn(6);
            celda.setCellStyle(estilo);
        }
    }
    public void exportar(HttpServletResponse response) throws IOException {
        escribirCabeceraDeTabla();
        escribirDatosDeLaTabla();
        
        ServletOutputStream outPutStream = response.getOutputStream();
        pedido.write(outPutStream);

        pedido.close();
        outPutStream.close();
    }       
}