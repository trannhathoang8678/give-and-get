package fpt.se1601.giveandget.interceptor;

import fpt.se1601.giveandget.reponsitory.entity.ApiEntity;
import java.util.ArrayList;
import java.util.List;

public class GatewayConstant {
    public static  List<ApiEntity> apiEntities = new ArrayList<>();
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static void addApiEntities(){
        apiEntities.add(new ApiEntity("index","/index","GET","MEMBER"));
    }
}
