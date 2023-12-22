package io.bootify.springbatch.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TransaccionDTO {

    private Long id;

    @NotNull
    private LocalDate fecha;

    @NotNull
    private Double cantidad;

    @NotNull
    @Size(max = 255)
    private String tipotransaccion;

    @NotNull
    @Size(max = 255)
    private String cuantaOrigen;

    @NotNull
    @Size(max = 255)
    private String cuentaDestino;

}
