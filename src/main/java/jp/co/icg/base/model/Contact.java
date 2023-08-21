package jp.co.icg.base.model;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import jp.co.icg.base.common.MsgConfig;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Contact {

    @NotEmpty(message=MsgConfig.ERR0001)
    @Size(max=30, message=MsgConfig.ERR0002)
    public String name;
    @Size(max=100, message=MsgConfig.ERR0003)
    @NotEmpty(message=MsgConfig.ERR0004)
    public String inquirySubject;
    @Email(message=MsgConfig.ERR0005)
    @Size(max=80, message=MsgConfig.ERR0006)
    public String email;
    @Size(max=4000, message=MsgConfig.ERR0007)
    @NotEmpty(message=MsgConfig.ERR0008)
    public String content;
}
