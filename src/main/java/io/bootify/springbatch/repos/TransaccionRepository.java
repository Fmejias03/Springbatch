package io.bootify.springbatch.repos;

import io.bootify.springbatch.domain.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TransaccionRepository extends JpaRepository<Transaccion, Long> {
}
