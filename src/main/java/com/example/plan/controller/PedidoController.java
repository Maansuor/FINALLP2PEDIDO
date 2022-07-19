/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.plan.controller;

import com.example.plan.entity.Pedido;

import com.example.plan.util.reportes.PedidoExporterExcel;

import com.example.plan.util.reportes.PedidoExporterPDF;
import com.lowagie.text.DocumentException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.plan.serviceImpl.PedidoService;

/**
 *
 * @author KAREN
 */
@Controller
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public String indexPedido(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("pedidos", pedidoService.readAll());
        return "pedidos/listarPedido";
    }

    @GetMapping("/add")
    public String addPedido(Model model) {
        model.addAttribute("titulo", "Registrar");
        model.addAttribute("pedido", new Pedido());
        return "pedidos/addPedido";
    }

    @GetMapping("/save")
    public String savePedido(Model model) {
        model.addAttribute("titulo", "Registrar");
        model.addAttribute("pedido", new Pedido());
        return "pedido/addPedido";
    }
    @PostMapping("/save")
    public String addPedido(@Valid @ModelAttribute Pedido pedido, BindingResult result, Model model, RedirectAttributes attributes ) {  
        pedidoService.create(pedido);
        return "redirect:/pedido";
    }

    /*@PostMapping("/save")
    public String addEmpleado(@Valid @ModelAttribute Pedido empleado, BindingResult result, Model model, @RequestParam("imagen") MultipartFile imagen, RedirectAttributes attributes ) {  

        if(!imagen.isEmpty()){
            String ruta = "C://imgmeraki//emple";
            
            try {
                byte[] bytesImg = imagen.getBytes();
                Path rutacompleta = Paths.get(ruta+"//"+imagen.getOriginalFilename());
                Files.write(rutacompleta, bytesImg);
                empleado.setImagen(imagen.getOriginalFilename());
                empleadoService.create(empleado);
            } catch (IOException e) {
                System.out.println("Error: "+e);
            }
        }
        return "redirect:/empleado";
    }*/
    
    @GetMapping("/edit/{id}")
    public String editarPedido(@PathVariable("id") int idpedido, Model model) { 
        Pedido pedido = pedidoService.read(idpedido);
        model.addAttribute("titulo", "Editar");
        model.addAttribute("pedido", pedido);
        return "pedidos/addPedido"; 
    }

    

    @GetMapping("/delete/{id}")
    public String deletePedido(@PathVariable("id") int idpedido) {
        pedidoService.delete(idpedido);
        return "redirect:/pedido";
    }

    @GetMapping("/exportarPDF")
    public void exportarListadoDePedidosEnPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");

        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String fechaActual = dateFormatter.format(new Date());

        String cabecera = "Content-Disposition";
        String valor = "attachment; filename=Pedido_" + fechaActual + ".pdf";

        response.setHeader(cabecera, valor);

        List<Pedido> pedidos = pedidoService.readAll();

        PedidoExporterPDF exporter = new PedidoExporterPDF(pedidos);
        exporter.exportar(response);
    }

    @GetMapping("/exportarExcel")
    public void exportarListadoDePedidoEnExcel(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/octet-stream");

        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String fechaActual = dateFormatter.format(new Date());

        String cabecera = "Content-Disposition";
        String valor = "attachment; filename=Pedido_" + fechaActual + ".xlsx";

        response.setHeader(cabecera, valor);

        List<Pedido> empleados = pedidoService.readAll();

        PedidoExporterExcel exporter = new PedidoExporterExcel(empleados);
        exporter.exportar(response);
    }
}
  