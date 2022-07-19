/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.plan.serviceImpl;

import com.example.plan.entity.Pedido;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.plan.repository.PedidoRepository;

/**
 *
 * @author KAREN
 */
@Service
public class PedidoServiceImpl implements PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;
    @Override
    public Pedido create(Pedido pedido) {
       return pedidoRepository.save(pedido);
    }

    @Override
    public Pedido update(Pedido pedido) {
       return pedidoRepository.save(pedido);
    }

    @Override
    public void delete(int id) {
        pedidoRepository.deleteById(id);
    }

    @Override
    public Pedido read(int id) {
        return pedidoRepository.findById(id).get();
    }

    @Override
    public List<Pedido> readAll() {
        return pedidoRepository.findAll();
    }
}