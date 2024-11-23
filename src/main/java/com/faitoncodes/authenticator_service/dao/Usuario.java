package com.faitoncodes.authenticator_service.dao;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity(name = "usuario")
@Table(name = "usuario")
@Getter
@Setter
public class Usuario implements UserDetails {
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      @Column(name = "user_id")
      private Long userId;

      @Column(name = "nome", nullable = false)
      private String nome;

      @Column(name = "email", nullable = false, unique = true)
      private String email;

      @Column(name = "senha", nullable = false)
      private String senha;

      @Column(name = "tipo_usuario", nullable = false)
      private Integer tipoUsuario;

      @Column(name = "color")
      private String color;

      public Usuario(String nome, String email, String senha, Integer tipoUsuario, String color) {
            this.nome = nome;
            this.email = email;
            this.senha = senha;
            this.tipoUsuario = tipoUsuario;
            this.color = color;
      }
      public Usuario(){
      }

      @Override
      public Collection<? extends GrantedAuthority> getAuthorities() {
            return List.of();
      }

      @Override
      public String getPassword() {
            return senha;
      }

      @Override
      public String getUsername() {
            return email;
      }

      @Override
      public boolean isAccountNonExpired() {
            return true;
      }

      @Override
      public boolean isAccountNonLocked() {
            return true;
      }

      @Override
      public boolean isCredentialsNonExpired() {
            return true;
      }

      @Override
      public boolean isEnabled() {
            return true;
      }
}