package fpt.se1601.giveandget.interceptor;

import fpt.se1601.giveandget.reponsitory.entity.ApiEntity;
import java.util.ArrayList;
import java.util.List;

public class GatewayConstant {
    public static  List<ApiEntity> apiEntities = new ArrayList<>();
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static void addApiEntities(){
        apiEntities.add(new ApiEntity("memberController","/member/.*","GET","MEMBER&MOD"));
        apiEntities.add(new ApiEntity("memberController","/member/.*","POST","MEMBER&MOD"));
        apiEntities.add(new ApiEntity("memberController","/member/.*","PUT","MEMBER&MOD"));
        apiEntities.add(new ApiEntity("memberController","/member/.*","DELETE","MEMBER&MOD"));
        apiEntities.add(new ApiEntity("getAllUser","/manager/user","GET","MOD"));
        apiEntities.add(new ApiEntity("deleteUser","/manager/user","DELETE","MOD"));
        apiEntities.add(new ApiEntity("getAllReport","/manager/report","GET","MOD"));
        apiEntities.add(new ApiEntity("replyReport","/manager/report/.*","PUT","MOD"));
    }
}
