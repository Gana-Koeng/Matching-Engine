package CSX.MatchingEngine.app.websocket.send;

import CSX.MatchingEngine.app.websocket.etc.RawSocketHandler;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@Log4j2
public class QuotationDataSending {

    public void sending(RawSocketHandler rawSocketHandler) {
        String msgSend = "Its work";
        log.info("Send growth board market data [{}]", msgSend); //console.log in intellij
            rawSocketHandler.sendAll(msgSend);

//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//        String formattedDate = sdf.format(broker.getOrderDate());
//
//        if (broker.getOrderType() == 1) {
//            String msgSend =    "A1"                                                      +
//                                format("%09d",broker.getIssueCode())                      +
//                                format("%04d",Integer.parseInt(broker.getBrokerId()))     +
//                                format("%04d",Integer.parseInt(broker.getAccountNo()))    +
//                                format("%08d",broker.getOriginalOrderNo())                +
//                                format("%08d", broker.getOrderUV())                       +
//                                format("%08d", broker.getOrderUV())                       + formattedDate;
//            log.info("Send growth board market data [{}]", msgSend); //console.log in intellij
//            rawSocketHandler.sendAll(msgSend);
//        }
//        if (broker.getOrderType() == 2) {
//            String msgSend =    "A2"                                                      +
//                                format("%09d",broker.getIssueCode())                      +
//                                format("%04d",Integer.parseInt(broker.getBrokerId()))     +
//                                format("%04d",Integer.parseInt(broker.getAccountNo()))    +
//                                format("%08d",broker.getOriginalOrderNo())                +
//                                format("%08d", broker.getOrderUV())                       +
//                                format("%08d", broker.getOrderUV())                       + formattedDate;
//            log.info("Send growth board market data [{}]", msgSend); //console.log in intellij
//            rawSocketHandler.sendAll(msgSend);
//        }
    }
}
