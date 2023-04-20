package CSX.MatchingEngine.app.websocket.etc;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

@Component
public class IpHandshakeInterceptor implements HandshakeInterceptor {
    private final String CLIENT_IP_KEY = "clientIp";

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
                                   WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        // Set ip attribute to WebSocket session
        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
            String ipAddress = servletRequest.getServletRequest().getHeader("X-FORWARDED-FOR");
            if (ipAddress == null) {
                ipAddress = servletRequest.getServletRequest().getRemoteAddr();
            }
            attributes.put(CLIENT_IP_KEY, ipAddress);
        }
        return true;
    }

    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
                               WebSocketHandler wsHandler, Exception exception) {
    }

    public String getClientIp(WebSocketSession session) {
        return (String) session.getAttributes().get(CLIENT_IP_KEY);
    }

}