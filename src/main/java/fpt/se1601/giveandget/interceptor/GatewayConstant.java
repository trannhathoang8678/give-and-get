package fpt.se1601.giveandget.interceptor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GatewayConstant {
    public static HashMap<String, List<String>> roleApiPath = new HashMap<>();
    public static final String CORRELATION_ID_HEADER = "correlation_id";
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String CLIENT_ID_HEADER = "client_id";
    public static final String CLIENT_SECRET_HEADER = "client_secret";
    public static final String X_IP_ADDRESS_HEADER = "X-IP-ADDRESS";
    public static final String START_PROCESSING_TIME = "start_processing";
    public static final String CLIENT_DETAIL = "client_detail";
    public static final String REQUESTED_SCOPE_DELIMITER = " ";
    public static final String ACCESS_TOKEN = "access_token";
    public static final String API = "api";
    public static final String PAYLOAD = "payload";
    public static final String PLAYER_ID = "player_id";
    public static final String ACCEPT_LANGUAGE = "Accept-Language";
    public static final String APP_VERSION = "app_version";
    public static void mapRoleApiPath(){
        List<String> memberApiPath = new ArrayList<>();
        roleApiPath.put("MEMBER",memberApiPath);
        List<String> moderatorApiPath = new ArrayList<>();
        roleApiPath.put("MODERATOR",moderatorApiPath);
        List<String> adminApiPath = new ArrayList<>();
        roleApiPath.put("ADMIN",adminApiPath);
    }
}