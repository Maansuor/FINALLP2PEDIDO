/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.plan.serviceImpl;

import com.example.plan.entity.Pedido;
import java.util.List;

/**
 *
 * @author KAREN
 */
public interface PedidoService {
    Pedido create(Pedido pedido);
    Pedido update(Pedido pedido);
    void delete(int id);
    Pedido read(int id);
    List<Pedido> readAll();     
}