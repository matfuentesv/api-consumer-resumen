package cl.hospital.apiproducersummary.service;


import cl.hospital.apiproducersummary.model.VitalSigns;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProducerAlertResumenService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private static final String ARCHIVO_JSON = "C:\\Desarrollo Cloud I\\api-consumer-resumen\\resourcesresumenes_vitales.json";
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final List<VitalSigns> resumenes = new ArrayList<>();

    @RabbitListener(queues = "summaryQueue")
    public void recibirResumen(VitalSigns vitalSigns) {
        System.out.println(" [x] Resumen recibido: " + vitalSigns);

        // Agregar el resumen a la lista en memoria
        resumenes.add(vitalSigns);

        // Guardar la lista en un archivo JSON
        guardarResumenesEnArchivo();
    }

    private void guardarResumenesEnArchivo() {
        try {
            // Crear o actualizar el archivo JSON
            File archivo = new File(ARCHIVO_JSON);

            // Escribir la lista de resúmenes en el archivo JSON
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(archivo, resumenes);
            System.out.println("Resúmenes guardados en: " + ARCHIVO_JSON);
        } catch (IOException e) {
            System.err.println("Error al guardar los resúmenes en el archivo JSON: " + e.getMessage());
        }
    }

}
