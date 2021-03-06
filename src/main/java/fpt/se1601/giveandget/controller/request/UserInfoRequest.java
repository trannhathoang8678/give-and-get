package fpt.se1601.giveandget.controller.request;


import lombok.Getter;
import lombok.Setter;


@Getter@Setter
public class UserInfoRequest {
    private int id;
    private String phone;
    private String name;
    private short sex;//ISO 5218
    private short age;
    private String linkContactInfo;
}
