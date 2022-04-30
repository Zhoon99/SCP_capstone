package kr.mmgg.scp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageDto {
    private Long userId;
    private String userNickname;
    private String messageContent;
    private String messageTime;
}
