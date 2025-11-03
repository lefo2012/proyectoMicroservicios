package co.edu.unicauca.administracionDocumental_ms.entities;

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
public class ProyectoDeGrado {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String titulo;

    private String objetivo;

    private String objetivoEspecifico;

    private Date   fechaSubida;

    private Date fechaRevision;

    private String archivoAdjunto;

    @ManyToOne
    private JefeDepartamento jefeDepartamento;
    @ManyToOne
    private Estudiante estudiante1;

    @ManyToOne
    private Estudiante estudiante2;

    @ManyToOne
    private Coordinador coordinador;

    @ManyToOne
    private Profesor director;

    @ManyToMany
    private List<Profesor> codirectores;

    @Transient
    private EstadoProyecto estadoProyecto;

    private String estado;
    @OneToMany
    private List<FormatoA> formatosA;

    @OneToMany
    private List<AnteProyecto> anteProyectos;



    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_proyecto")
    private TipoProyecto tipoProyecto;

    //nos iba a volver locos
    private String descripcion = "Un proyecto de grado";

    public ProyectoDeGrado(TipoProyecto tipoProyecto)
    {
        this.tipoProyecto=tipoProyecto;
    }

    public void setEstudiante2 (Estudiante estudiante){
        this.estudiante2 = estudiante;
        this.estudiante2.addProyectoDeGrado(this);
    }
    public ProyectoDeGrado()
    {
        codirectores = new ArrayList<>();
        formatosA = new ArrayList<>();
        anteProyectos = new ArrayList<>();
    }

    public void setDirector(Profesor director){
        this.director = director;
        this.director.addProyectoDeGradoDirigido(this);
    }

    public void setEstudiante1 (Estudiante estudiante){
        this.estudiante1 = estudiante;
        this.estudiante1.addProyectoDeGrado(this);
    }

    public void setCoordinador(Coordinador coordinador){
        this.coordinador = coordinador;
        this.coordinador.addProyectoDeGrado(this);
    }

    public void setCodirectores(List<Profesor> codirectores) {
        if (codirectores == null) {
            return;
        }

        this.codirectores = codirectores;

        for (Profesor codirector : codirectores) {
            codirector.addProyectoDeGradoCodirigidos(this);
        }
    }


    public void setEstadoProyecto(EstadoProyecto estadoProyecto){
        this.estadoProyecto = estadoProyecto;
        estado=estadoProyecto.getNombre();
    }

    public String getEstadoProyecto()
    {
        return estado;
    }

    public void aprobar()
    {
        estadoProyecto.aprobar(this);
    }

    public void rechazar()
    {
        estadoProyecto.rechazar(this);
    }
    public void correccion()
    {
        estadoProyecto.correciones(this);
    }
    public void aumentarIntentosEstudiantes()
    {
        if(tipoProyecto.equals(TipoProyecto.PRACTICA_LABORAL))
        {
            estudiante1.aumentarCantidadIntentosPracticaLaboral();
        }else if(estudiante2 != null)
        {
            estudiante1.aumentarCantidadIntentosInvestigacion();
            estudiante2.aumentarCantidadIntentosInvestigacion();
        }else {
            estudiante1.aumentarCantidadIntentosInvestigacion();
        }
    }
    public boolean disponibilidadEstudiantes()
    {
        if(tipoProyecto.equals(TipoProyecto.PRACTICA_LABORAL))
        {
            return estudiante1.practicaLaboralDisponible();
        }
        if(tipoProyecto.equals(TipoProyecto.INVESTIGACION))
        {
            if(estudiante2!=null)
            {
                return estudiante2.investigacionDisponible() && estudiante1.investigacionDisponible();
            }
            else
            {
                return estudiante1.investigacionDisponible();
            }
        }
        return false;
    }
    public void addAnteProyecto(AnteProyecto anteProyecto)
    {
        anteProyectos.add(anteProyecto);
    }
}
