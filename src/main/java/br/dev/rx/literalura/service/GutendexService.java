package io.literatura.servicio;

import io.literatura.dto.AutorDTO;
import io.literatura.dto.LibroDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class GutendexServicio {
    private static final String URL_API = "https://gutendex.com/books/";

    public List<LibroDTO> buscarLibros(String consulta) throws Exception {
        HttpClient cliente = HttpClient.newHttpClient();
        String consultaCodificada = URLEncoder.encode(consulta, StandardCharsets.UTF_8);
        HttpRequest solicitud = HttpRequest.newBuilder().uri(new URI(URL_API + "?search=" + consultaCodificada))
                .build();

        HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());

        if (respuesta.statusCode() == 200) {
            String respuestaJson = respuesta.body();
            ObjectMapper objectMapper = new ObjectMapper();
            RespuestaGutendex respuestaGutendex = objectMapper.readValue(respuestaJson, RespuestaGutendex.class);
            return respuestaGutendex.getResultados();
        } else {
            throw new RuntimeException("Error al buscar libros: " + respuesta.statusCode());
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class RespuestaGutendex {
        private List<LibroDTO> resultados;

        public List<LibroDTO> getResultados() {
            return resultados;
        }
    }
}
    }
}
