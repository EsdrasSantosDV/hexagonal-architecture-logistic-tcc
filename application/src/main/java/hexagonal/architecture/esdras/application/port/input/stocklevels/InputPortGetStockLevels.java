package hexagonal.architecture.esdras.application.port.input.stocklevels;

import hexagonal.architecture.esdras.application.port.input.stocklevels.commands.GetStockLevelsCommand;
import hexagonal.architecture.esdras.application.port.input.stocklevels.commands.ResultStockProducts;
import hexagonal.architecture.esdras.application.port.input.stocklevels.exceptions.StockNotFoundException;

public interface InputPortGetStockLevels {

    ResultStockProducts getStockLevels(GetStockLevelsCommand getStockLevelsCommand) throws StockNotFoundException;

}
