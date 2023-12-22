package io.bootify.springbatch.config;

import io.bootify.springbatch.domain.Transaccion;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.EntityManagerFactory;


public class TransaccionItemWriter extends JpaItemWriter<Transaccion> {

    @Autowired
    public TransaccionItemWriter(EntityManagerFactory entityManagerFactory) {
        setEntityManagerFactory(entityManagerFactory);
    }
}

