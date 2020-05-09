package br.com.avaliacao.bluebank.model;

import javax.persistence.*;

@Entity(name="USUARIO_ROLE")
public class UsuarioRole {

    @Id
    @Column(name = "ID")
    private Long idUsuario;

    @Column(name="role_id")
    private Long roleId;

    public UsuarioRole(){
    }

    public UsuarioRole(Usuario usuario,Role role){
        this.setIdUsuario(usuario.getId());
        this.setRoleId(role.getId());
    }


    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /*public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }*/
}
