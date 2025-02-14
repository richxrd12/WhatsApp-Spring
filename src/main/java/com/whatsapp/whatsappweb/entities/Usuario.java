package com.whatsapp.whatsappweb.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "usuarios")
@Schema(description = "Representa al usuario")
public class Usuario {
    @Id
    @Schema(description = "Identificador único del usuario", example = "ObjectId('67af5e0c54f475316e75373b')")
    private ObjectId id;

    @Schema(description = "Nombre del usuario", example = "Richard")
    private String nombre;

    @Schema(description = "Apellidos del usuario", example = "Pérez Padilla")
    private String apellidos;

    @Schema(description = "Estado del usuario", example = "Ocupado")
    private String estado;

    @Schema(description = "Identificador único del usuario textual", example = "richa")
    private String usuario;

    @Schema(description = "Contraseña del usuario", example = "coche1234")
    private String password;
}
