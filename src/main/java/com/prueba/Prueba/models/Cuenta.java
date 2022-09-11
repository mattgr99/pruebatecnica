package com.prueba.Prueba.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cuenta {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long cuentaid;
    private String numerocuenta;
    private String tipoCuenta;
    private double saldo;
    private boolean estado;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    @JoinColumn(name = "fk_cliente",referencedColumnName="clienteid")
    private Cliente cliente;

    @OneToMany(mappedBy = "cuenta",fetch= FetchType.LAZY)
    private List<Movimiento> movimientos;
}
