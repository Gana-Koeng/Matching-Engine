package CSX.MatchingEngine.app;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponse {

    private Integer resCode;

    private String resMsg;

    private Object Data;

}
