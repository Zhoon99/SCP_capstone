package kr.mmgg.scp.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import kr.mmgg.scp.entity.Task;
import lombok.Data;

@Data
public class ProjectDetailMyTaskDto {
   private List<Task> taskList;
}
