/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ubam.tiendaRopa.Service;

import com.ubam.tiendaRopa.Entity.Usuario;
import com.ubam.tiendaRopa.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlo
 */
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepo;

    public Usuario login(String correo, String contra){
    return usuarioRepo.findByUsuarioCorreoAndUsuarioContra(correo, contra);
}

    public void registrar(Usuario usuario){
        usuarioRepo.save(usuario);
    }
}