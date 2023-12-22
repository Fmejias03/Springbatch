package io.bootify.springbatch.config;

import io.bootify.springbatch.domain.Transaccion;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class CustomFieldSetMapper implements FieldSetMapper<Transaccion> {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public Transaccion mapFieldSet(FieldSet fieldSet) throws BindException {
        Transaccion transaccion = new Transaccion();
        transaccion.setId(fieldSet.readLong("id"));
        transaccion.setFecha(LocalDate.parse(fieldSet.readString("fecha"), formatter));
        transaccion.setCantidad(fieldSet.readDouble("cantidad"));
        transaccion.setTipotrans(fieldSet.readString("tipotrans"));
        transaccion.setCuentaorigen(fieldSet.readString("cuentaorigen"));
        transaccion.setCuentadestino(fieldSet.readString("cuentadestino"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(fieldSet.readString("dateCreated"), formatter);
        OffsetDateTime offsetDateTime = date.atStartOfDay().atOffset(ZoneOffset.UTC);
        transaccion.setDateCreated(offsetDateTime);

        date = LocalDate.parse(fieldSet.readString("lastUpdated"), formatter);
        offsetDateTime = date.atStartOfDay().atOffset(ZoneOffset.UTC);
        transaccion.setLastUpdated(offsetDateTime);

        return transaccion;
    }
}
