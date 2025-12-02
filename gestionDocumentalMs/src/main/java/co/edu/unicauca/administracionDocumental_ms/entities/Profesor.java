package co.edu.unicauca.administracionDocumental_ms.entities;


import co.edu.unicauca.administracionDocumental_ms.builder.Director;
import co.edu.unicauca.administracionDocumental_ms.builder.InvestigacionBuilder;
import co.edu.unicauca.administracionDocumental_ms.builder.PracticaBuilder;
import co.edu.unicauca.administracionDocumental_ms.factory.StateFactory;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Profesor extends Persona{

    List<AnteProyecto> anteProyectos;
    private List<AnteProyecto> anteProyectosEvaluadosComo1 ;
    private List<AnteProyecto> anteProyectosEvaluadosComo2 ;
    List<ProyectoDeGrado> proyectosDeGradoDirigidos;
    List<ProyectoDeGrado> proyectosDeGradoCodirigidos;
    Departamento departamento;

    public Profesor()
    {
        this.anteProyectos=new ArrayList<>();
        this.anteProyectosEvaluadosComo1=new ArrayList<>();
        this.anteProyectosEvaluadosComo2=new ArrayList<>();
        this.proyectosDeGradoDirigidos=new ArrayList<>();
        this.proyectosDeGradoCodirigidos=new ArrayList<>();
    }

    public List<AnteProyecto> getAnteProyectos() {
        return anteProyectos;
    }

    public void setAnteProyectos(List<AnteProyecto> anteProyectos) {
        this.anteProyectos = anteProyectos;
    }

    public List<AnteProyecto> getAnteProyectosEvaluadosComo1() {
        return anteProyectosEvaluadosComo1;
    }

    public void setAnteProyectosEvaluadosComo1(List<AnteProyecto> anteProyectosEvaluadosComo1) {
        this.anteProyectosEvaluadosComo1 = anteProyectosEvaluadosComo1;
    }

    public List<AnteProyecto> getAnteProyectosEvaluadosComo2() {
        return anteProyectosEvaluadosComo2;
    }

    public void setAnteProyectosEvaluadosComo2(List<AnteProyecto> anteProyectosEvaluadosComo2) {
        this.anteProyectosEvaluadosComo2 = anteProyectosEvaluadosComo2;
    }

    public List<ProyectoDeGrado> getProyectosDeGradoDirigidos() {
        return proyectosDeGradoDirigidos;
    }

    public void setProyectosDeGradoDirigidos(List<ProyectoDeGrado> proyectosDeGradoDirigidos) {
        this.proyectosDeGradoDirigidos = proyectosDeGradoDirigidos;
    }

    public List<ProyectoDeGrado> getProyectosDeGradoCodirigidos() {
        return proyectosDeGradoCodirigidos;
    }

    public void setProyectosDeGradoCodirigidos(List<ProyectoDeGrado> proyectosDeGradoCodirigidos) {
        this.proyectosDeGradoCodirigidos = proyectosDeGradoCodirigidos;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public boolean relacionarDepartamento(Departamento departamento)
    {
        if(this.departamento==null)
        {
            this.departamento=departamento;
            departamento.relacionarProfesor(this);
            return true;
        }
        return false;
    }



    public ProyectoDeGrado iniciarProyectoDeGradoInvestigacion(String titulo, String objetivo, String objetivoEspecifico, String archivoAdjunto, Estudiante estudiante1, Estudiante estudiante2, List<Profesor> codirectores) throws Exception {

        if(!estudiante1.disponible() && !estudiante1.investigacionDisponible())
        {
            throw new RuntimeException("Estudiante: "+estudiante1.getNombre()+" no disponible");
        }
        if(estudiante2!=null){
            if(!estudiante2.disponible() && !estudiante2.investigacionDisponible())
            {
                throw new RuntimeException("Estudiante: "+estudiante2.getNombre()+" no disponible");
            }
        }


        Director director = new Director();
        director.setBuilder(new InvestigacionBuilder());

        director.build(
                titulo,
                objetivo,
                objetivoEspecifico,
                new Date(),
                archivoAdjunto,
                estudiante1,
                estudiante2,
                this.departamento.getCoordinador(),
                this,
                codirectores
        );

        return director.getProyectoDeGrado();
    }
    public ProyectoDeGrado iniciarProyectoDeGradoPracticaLaboral(String titulo, String objetivo, String objetivoEspecifico, String archivoAdjunto, Estudiante estudiante1, Estudiante estudiante2, List<Profesor> codirectores) throws Exception {

        if(!estudiante1.disponible() && !estudiante1.practicaLaboralDisponible())
        {
            throw new RuntimeException("Estudiante: "+estudiante1.getNombre()+" no disponible");
        }

        Director director = new Director();
        director.setBuilder(new PracticaBuilder());

        director.build(
                titulo,
                objetivo,
                objetivoEspecifico,
                new Date(),
                archivoAdjunto,
                estudiante1,
                estudiante2,
                this.departamento.getCoordinador(),
                this,
                codirectores
        );

        return director.getProyectoDeGrado();
    }
    public ProyectoDeGrado subirAnteproyecto(ProyectoDeGrado proyectoDeGrado,AnteProyecto anteProyecto)
    {
        StateFactory stateFactory = StateFactory.getInstance();
        if(proyectoDeGrado.getEstado().equals("APROBADO"))
        {
            proyectoDeGrado.setJefeDepartamento(this.departamento.getJefeDepartamento());
            this.departamento.getJefeDepartamento().addProyectoDeGrado(proyectoDeGrado);
            proyectoDeGrado.addAnteProyecto(anteProyecto);
            proyectoDeGrado.setEstadoProyecto(stateFactory.getInstance("REVISION_ANTEPROYECTO"));
        }
        return proyectoDeGrado;
    }

    public boolean addProyectoDeGradoDirigido(ProyectoDeGrado proyectoDeGrado)
    {
        if(!this.proyectosDeGradoDirigidos.contains(this)){
            this.proyectosDeGradoDirigidos.add(proyectoDeGrado);
            return true;
        }
        return false;
    }
    public boolean addProyectoDeGradoCodirigidos(ProyectoDeGrado proyectoDeGrado)
    {
        if(!this.proyectosDeGradoCodirigidos.contains(this)){
            this.proyectosDeGradoCodirigidos.add(proyectoDeGrado);
            return true;
        }
        return false;

    }

}
