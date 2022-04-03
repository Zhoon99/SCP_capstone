package kr.mmgg.scp.dto.response;

import kr.mmgg.scp.entity.Task;
import lombok.Data;

@Data
public class RequestTaskDto {
    private Task reqTask;
}
