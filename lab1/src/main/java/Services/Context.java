package Services;

import Entities.IBank;
import Entities.IClient;
import lombok.Getter;
import lombok.Setter;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;

@Getter
public class Context
{
    private final TextIO _textIO = TextIoFactory.getTextIO();
    private final ICentralBank _centralBank = new CentralBank();
    @Setter private IBank _currentBank;
    @Setter private IClient _currentClient;
}
