package com.whatsapp.whatsappweb.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "mensajes")
@Schema(description = "Representan los mensajes entre usuarios")
public class Mensaje {
    @Id
    @Schema(description = "Identificador único del usuario", example = "ObjectId('67af5e0c54f475316e75373b')")
    private ObjectId id;

    @Schema(description = "Identificador único del usuario emisor por usuario", example = "cynthia")
    private String usuarioEmisor;

    @Schema(description = "Identificador único del usuario receptor del mensaje por usuario", example = "richa")
    private String usuarioReceptor;

    @Schema(description = "Fecha y hora a la que fue mandada (aunque solo muestro la fecha)", example = "2025/12/05")
    private LocalDateTime fecha;

    @Schema(description = "Contenido del mensaje", example = "Buenos días princesa")
    private String texto;

    @Schema(description = "Muestra si el mensaje ha sido editado o no", example = "true")
    private boolean edited;
}
