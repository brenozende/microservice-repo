package br.com.brenozende.microservice.fornecedor.model;


import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Pedido {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer tempoDePreparo;

    @Enumerated(EnumType.STRING)
    private PedidoStatus status;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "pedidoId")
    @ToString.Exclude
    private List<PedidoItem> itens;

    public Pedido(List<PedidoItem> itens) {
        this.itens = itens;
        this.status = PedidoStatus.RECEBIDO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Pedido pedido = (Pedido) o;
        return id != null && Objects.equals(id, pedido.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
