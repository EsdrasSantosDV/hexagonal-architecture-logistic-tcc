package hexagonal.architecture.esdras.application.port.input.stocklevels;

import hexagonal.architecture.esdras.application.port.input.stocklevels.commands.GetStockLevelsCommand;
import hexagonal.architecture.esdras.application.port.input.stocklevels.exceptions.StockNotFoundException;

public interface InputPortGetStockLevels {

    void getStockLevels(GetStockLevelsCommand getStockLevelsCommand) throws StockNotFoundException;

}
