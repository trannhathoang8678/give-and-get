package fpt.se1601.giveandget.controller.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateRoleRequest {
    private int userId;
    private String role;
}
