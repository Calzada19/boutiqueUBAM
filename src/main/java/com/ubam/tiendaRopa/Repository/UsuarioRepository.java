/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ubam.tiendaRopa.Repository;

import com.ubam.tiendaRopa.Entity.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author carlos
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
    Usuario findByUsuarioCorreoAndUsuarioContra(String correo, String contra);
}
