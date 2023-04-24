package CSX.MatchingEngine.app.Entity.execution;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
public class ExecutionID implements Serializable {

    private int orderNo;
    private Date orderDate;
    private String brokerId;
    private int contractNo;
}
