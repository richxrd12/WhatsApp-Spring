package com.whatsapp.whatsappweb.entities;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "mensajes")
public class Mensaje {
    private String usuarioEmisor;
    private String usuarioReceptor;
    private LocalDateTime fecha;
    private String texto;
}
