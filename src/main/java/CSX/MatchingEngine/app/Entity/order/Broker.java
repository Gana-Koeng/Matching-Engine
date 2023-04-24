package CSX.MatchingEngine.app.Entity.order;

import lombok.Getter;
import lombok.Setter;
import java.math.BigInteger;
import java.sql.Date;

@Setter
@Getter
public class Broker{

    private int orderNo;
    private Date orderDate;
    private String brokerId;
    private String accountNo;
    private int orderType;
    private int issueCode;
    private int originalOrderNo;
    private int orderQty;
    private BigInteger orderUV;

}
