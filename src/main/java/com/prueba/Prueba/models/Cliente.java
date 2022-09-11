package com.prueba.Prueba.models;

import lombok.AllArgsConstructor;
import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.AUTO;

@Entity
@AllArgsConstructor
public class Cliente extends Persona{
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long clienteid;
    private String contrasenia;
    private boolean estado;
    @OneToMany(mappedBy = "cliente",fetch= FetchType.LAZY)
    private List<Cuenta> cuentas;

    public Cliente() {
        super();
    }

    public Long getClienteid() {
        return clienteid;
    }

    public void setClienteid(Long clienteid) {
        this.clienteid = clienteid;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }
}

