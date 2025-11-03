package co.edu.unicauca.administracionDocumental_ms.rabbitConfig;

@Service
public class PersonaConsumer {

    @Autowired
    private PersonaService personaService;

    @RabbitListener(queues = "persona_queue")
    public void recibirPersona(PersonaDto persona) {
        try {
            System.out.println("📩 Persona recibida desde RabbitMQ: " + persona.getCorreoElectronico());
            personaService.guardar(personaService.mapearDto(persona));
        } catch (Exception e) {
            System.err.println("❌ Error al guardar persona: " + e.getMessage());
        }
    }
}