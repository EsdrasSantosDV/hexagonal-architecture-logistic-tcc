package hexagonal.architecture.esdras.application.port.output.stock.persistence;

import hexagonal.architecture.esdras.domain.entity.StockDomain;

import java.util.Optional;

public interface OutputPortStock {

    StockDomain save(StockDomain stockDomain);

    Optional<StockDomain> findById(String id);
}
