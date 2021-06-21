package fpt.se1601.giveandget.controller.request;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter@Setter
public class UserInfoRequest {
    private int id;
    private String phone;
    private String name;
    private String avatar;
    private short sex;//ISO 5218
    private short age;
    private String linkContactInfo;
}
