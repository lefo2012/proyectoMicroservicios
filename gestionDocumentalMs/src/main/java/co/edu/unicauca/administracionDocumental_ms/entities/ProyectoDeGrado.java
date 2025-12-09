package co.edu.unicauca.administracionDocumental_ms.entities;

import co.edu.unicauca.administracionDocumental_ms.state.EstadoProyecto;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ProyectoDeGrado {

    private long id;

    private String titulo;

    private String objetivo;

    private String objetivoEspecifico;

    private Date   fechaSubida;

    private Date fechaRevision;

    private String archivoAdjunto;

    private JefeDepartamento jefeDepartamento;

    private Estudiante estudiante1;

    private Estudiante estudiante2;

    private List<Estudiante> estudiantes;

    private Coordinador coordinador;

    private Profesor director;

    private List<Profesor> codirectores;

    private EstadoProyecto estadoProyecto;

    private String estado;

    private List<FormatoA> formatosA;

    private List<AnteProyecto> anteProyectos;

    private TipoProyecto tipoProyecto;

    private String descripcion = "Un proyecto de grado";

    public ProyectoDeGrado(TipoProyecto tipoProyecto)
    {
        this.tipoProyecto=tipoProyecto;
    }

    public long getId() {return id;}

    public void setId(long id) {this.id = id;}

    public String getTitulo() {return titulo;}

    public void setTitulo(String titulo) {this.titulo = titulo;}

    public String getObjetivo() {return objetivo;}

    public void setObjetivo(String objetivo) {this.objetivo = objetivo;}

    public String getObjetivoEspecifico() {return objetivoEspecifico;}

    public void setObjetivoEspecifico(String objetivoEspecifico) {this.objetivoEspecifico = objetivoEspecifico;}

    public Date getFechaSubida() {return fechaSubida;}

    public void setFechaSubida(Date fechaSubida) {this.fechaSubida = fechaSubida;}

    public Date getFechaRevision() {return fechaRevision;}

    public void setFechaRevision(Date fechaRevision) {this.fechaRevision = fechaRevision;}

    public String getArchivoAdjunto() {return archivoAdjunto;}

    public void setArchivoAdjunto(String archivoAdjunto) {this.archivoAdjunto = archivoAdjunto;}

    public JefeDepartamento getJefeDepartamento() {return jefeDepartamento;}

    public void setJefeDepartamento(JefeDepartamento jefeDepartamento) {this.jefeDepartamento = jefeDepartamento;}

    public Estudiante getEstudiante1() {return estudiante1;}

    public Estudiante getEstudiante2() {return estudiante2;}

    public Coordinador getCoordinador() {return coordinador;}

    public Profesor getDirector() {return director;}

    public List<Profesor> getCodirectores() {return codirectores;}

    public String getEstado() {return estado;}

    public void setEstado(String estado) {this.estado = estado;}

    public List<FormatoA> getFormatosA() {return formatosA;}

    public void setFormatosA(List<FormatoA> formatosA) {this.formatosA = formatosA;}

    public List<AnteProyecto> getAnteProyectos() {return anteProyectos;}

    public void setAnteProyectos(List<AnteProyecto> anteProyectos) {this.anteProyectos = anteProyectos;}

    public TipoProyecto getTipoProyecto() {return tipoProyecto;}

    public void setTipoProyecto(TipoProyecto tipoProyecto) {this.tipoProyecto = tipoProyecto;}

    public String getDescripcion() {return descripcion;}

    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(List<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
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
        estudiantes = new ArrayList<>();
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
        if (!this.anteProyectos.contains(anteProyecto)) {
            this.anteProyectos.add(anteProyecto);
            anteProyecto.setProyectoDeGrado(this);
        }
    }
}
