package com.foro.alura.domain.respuestas;

public record DatosListRespuesta(
        Long id,
        String mensaje,
        String fechaDeCreacion,
        Boolean solucion
) {
    public DatosListRespuesta(Respuestas respuestas) {
        this(respuestas.getId(), respuestas.getMensaje(), respuestas.getFechaDeCreacion().toString(), respuestas.getSolucion());
    }

}
