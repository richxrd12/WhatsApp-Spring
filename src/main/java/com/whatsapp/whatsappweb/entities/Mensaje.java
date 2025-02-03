package com.whatsapp.whatsappweb.entities;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "mensajes")
public class Mensaje {

}
