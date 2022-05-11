package kr.mmgg.scp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StompMessageDto {
    private Long chatinuserId;
    private String chatinuserName;
    private String messageContent;
    private String messageTime;
}
