package fpt.se1601.giveandget.interceptor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GatewayConstant {
    public static HashMap<String, List<String>> roleApiPath = new HashMap<>();
    public static void mapRoleApiPath(){
        List<String> memberApiPath = new ArrayList<>();
        roleApiPath.put("MEMBER",memberApiPath);
        List<String> moderatorApiPath = new ArrayList<>();
        roleApiPath.put("MODERATOR",moderatorApiPath);
        List<String> adminApiPath = new ArrayList<>();
        roleApiPath.put("ADMIN",adminApiPath);
    }
}
