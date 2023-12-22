package io.bootify.springbatch.config;

import io.bootify.springbatch.domain.Transaccion;
import org.springframework.batch.item.ItemProcessor;

public class TransaccionProcessor implements ItemProcessor<Transaccion, Transaccion> {

    @Override
    public Transaccion process(Transaccion transaccion) {
        return transaccion;
    }
}

