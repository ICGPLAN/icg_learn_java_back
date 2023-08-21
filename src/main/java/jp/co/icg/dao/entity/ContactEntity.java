package jp.co.icg.dao.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor //有参构造器
@NoArgsConstructor  //无参构造器
@Builder
@TableName(value = "contact")
public class ContactEntity {
    @TableId(value = "contact_id")
    private String contactId;
    @TableField(value = "name")
    private String name;
    @TableField(value = "inquirySubject")
    private String inquirySubject;
    @TableField(value = "email")
    private String email;
    @TableField(value = "content")
    private String content;
    @TableField(value = "creation_time")
    private LocalDateTime creationTime;
    @TableField(value = "deleted_flag")
    private boolean deletedFlag;
    @TableField(value = "read_flag")
    private boolean readFlag;
}
