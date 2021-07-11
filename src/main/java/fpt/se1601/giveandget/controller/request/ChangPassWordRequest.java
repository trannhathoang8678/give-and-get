package fpt.se1601.giveandget.controller.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangPassWordRequest {
    private String email;
    private String oldPassword;
    private String newPassWord;
}
