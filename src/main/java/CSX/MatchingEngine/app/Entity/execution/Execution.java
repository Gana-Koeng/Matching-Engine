package CSX.MatchingEngine.app.Entity.execution;

//import csx.broker.Entity.broker.BrokerId;
import com.google.api.client.util.DateTime;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Date;
//import java.util.ArrayList;
//import java.util.List;

//create table and variable match to database
@Entity
@Setter
@Getter
@Data
@Table(name = "execution_table")
@IdClass(ExecutionID.class)
public class Execution implements Serializable {

    @Id
    @Column(name= "order_no",columnDefinition = "Int")
    private int orderNo;
    @Id
    @Column(name= "order_date",columnDefinition = "Date")
    private Date orderDate;
    @Id
    @Column(name= "broker_id",columnDefinition = "String")
    private String brokerId;
    @Id
    @Column(name= "contract_no",columnDefinition = "int")
    private int contractNo;
    @Column(name= "account_no",columnDefinition = "String")
    private String accountNo;
    @Column(name= "order_type",columnDefinition = "int")
    private int orderType;
    @Column(name= "issue_code",columnDefinition = "String")
    private String issueCode;
    @Column(name= "contract_qty",columnDefinition = "Int")
    private int contractQty;
    @Column(name= "contract_uv",columnDefinition = "BigInt")
    private BigInteger contractUV;
    @Column(name= "contract_time",columnDefinition = "DateTime")
    private DateTime contractTime;





}
