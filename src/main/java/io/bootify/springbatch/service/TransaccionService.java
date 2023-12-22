package io.bootify.springbatch.service;

import io.bootify.springbatch.domain.Transaccion;
import io.bootify.springbatch.model.TransaccionDTO;
import io.bootify.springbatch.repos.TransaccionRepository;
import io.bootify.springbatch.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class TransaccionService {

    private final TransaccionRepository transaccionRepository;

    public TransaccionService(final TransaccionRepository transaccionRepository) {
        this.transaccionRepository = transaccionRepository;
    }

    public List<TransaccionDTO> findAll() {
        final List<Transaccion> transaccions = transaccionRepository.findAll(Sort.by("id"));
        return transaccions.stream()
                .map(transaccion -> mapToDTO(transaccion, new TransaccionDTO()))
                .toList();
    }

    public TransaccionDTO get(final Long id) {
        return transaccionRepository.findById(id)
                .map(transaccion -> mapToDTO(transaccion, new TransaccionDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final TransaccionDTO transaccionDTO) {
        final Transaccion transaccion = new Transaccion();
        mapToEntity(transaccionDTO, transaccion);
        return transaccionRepository.save(transaccion).getId();
    }

    public void update(final Long id, final TransaccionDTO transaccionDTO) {
        final Transaccion transaccion = transaccionRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(transaccionDTO, transaccion);
        transaccionRepository.save(transaccion);
    }

    public void delete(final Long id) {
        transaccionRepository.deleteById(id);
    }

    private TransaccionDTO mapToDTO(final Transaccion transaccion,
            final TransaccionDTO transaccionDTO) {
        transaccionDTO.setId(transaccion.getId());
        transaccionDTO.setFecha(transaccion.getFecha());
        transaccionDTO.setCantidad(transaccion.getCantidad());
        transaccionDTO.setTipotransaccion(transaccion.getTipotransaccion());
        transaccionDTO.setCuantaOrigen(transaccion.getCuantaOrigen());
        transaccionDTO.setCuentaDestino(transaccion.getCuentaDestino());
        return transaccionDTO;
    }

    private Transaccion mapToEntity(final TransaccionDTO transaccionDTO,
            final Transaccion transaccion) {
        transaccion.setFecha(transaccionDTO.getFecha());
        transaccion.setCantidad(transaccionDTO.getCantidad());
        transaccion.setTipotransaccion(transaccionDTO.getTipotransaccion());
        transaccion.setCuantaOrigen(transaccionDTO.getCuantaOrigen());
        transaccion.setCuentaDestino(transaccionDTO.getCuentaDestino());
        return transaccion;
    }

}
