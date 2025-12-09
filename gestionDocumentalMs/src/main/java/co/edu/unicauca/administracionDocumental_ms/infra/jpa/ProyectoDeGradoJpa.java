package co.edu.unicauca.administracionDocumental_ms.infra.jpa;

import co.edu.unicauca.administracionDocumental_ms.entities.*;
import co.edu.unicauca.administracionDocumental_ms.state.EstadoProyecto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class ProyectoDeGradoJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String titulo;

    private String objetivo;

    private String objetivoEspecifico;

    private Date fechaSubida;

    private Date fechaRevision;

    private String archivoAdjunto;

    @ManyToOne
    @JoinColumn(name = "jefeDepartamento_id")
    private JefeDepartamentoJpa jefeDepartamento;

    @ManyToOne
    @JoinColumn(name = "estudiante1_id")
    private EstudianteJpa estudiante1;

    @ManyToOne
    @JoinColumn(name = "estudiante2_id")
    private EstudianteJpa estudiante2;

    @ManyToOne
    @JoinColumn(name = "coordinador_id")
    private CoordinadorJpa coordinador;

    @ManyToOne
    private ProfesorJpa director;

    @ManyToMany
    private List<ProfesorJpa> codirectores;

    @Transient
    private EstadoProyecto estadoProyecto;

    private String estado;
    @OneToMany
    private List<FormatoAJpa> formatosA;

    @OneToMany
    private List<AnteProyectoJpa> anteProyectos;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_proyecto")
    private TipoProyecto tipoProyecto;


    @ManyToMany
    List<EstudianteJpa> estudiantes;

    private String descripcion = "Un proyecto de grado";

    public ProyectoDeGradoJpa(TipoProyecto tipoProyecto)
    {
        this.tipoProyecto=tipoProyecto;
    }
    public ProyectoDeGradoJpa()
    {
        codirectores = new ArrayList<>();
        formatosA = new ArrayList<>();
        anteProyectos = new ArrayList<>();
        estudiantes = new ArrayList<>();
    }


}
