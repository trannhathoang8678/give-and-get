package fpt.se1601.giveandget.interceptor;

import fpt.se1601.giveandget.reponsitory.entity.ApiEntity;
import java.util.ArrayList;
import java.util.List;

public class GatewayConstant {
    public static  List<ApiEntity> apiEntities = new ArrayList<>();
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static void addApiEntities(){
        apiEntities.add(new ApiEntity("adminController","/manager/.*","GET","ADMIN"));
        apiEntities.add(new ApiEntity("adminController","/manager/.*","POST","ADMIN"));
        apiEntities.add(new ApiEntity("adminController","/manager/.*","PUT","ADMIN"));
        apiEntities.add(new ApiEntity("adminController","/manager/.*","DELETE","ADMIN"));
        apiEntities.add(new ApiEntity("memberController","/member/.*","GET","MEMBER"));
        apiEntities.add(new ApiEntity("memberController","/member/.*","POST","MEMBER"));
        apiEntities.add(new ApiEntity("memberController","/member/.*","PUT","MEMBER"));
        apiEntities.add(new ApiEntity("memberController","/member/.*","DELETE","MEMBER"));
        apiEntities.add(new ApiEntity("moderatorController","/member/.*","GET","MOD"));
        apiEntities.add(new ApiEntity("moderatorController","/member/.*","POST","MOD"));
        apiEntities.add(new ApiEntity("moderatorController","/member/.*","PUT","MOD"));
        apiEntities.add(new ApiEntity("moderatorController","/member/.*","DELETE","MOD"));
        apiEntities.add(new ApiEntity("getAllUser","/manager/user","GET","MOD"));
        apiEntities.add(new ApiEntity("deleteUser","/manager/user","DELETE","MOD"));
        apiEntities.add(new ApiEntity("getAllReport","/manager/report","GET","MOD"));
        apiEntities.add(new ApiEntity("replyReport","/manager/report/.*","PUT","MOD"));
        apiEntities.add(new ApiEntity("index","/index","GET","MEMBER"));
    }
}
