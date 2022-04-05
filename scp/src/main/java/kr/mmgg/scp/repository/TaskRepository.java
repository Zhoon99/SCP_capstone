package kr.mmgg.scp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import kr.mmgg.scp.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    // @Query(value = "select * from task t join projectinuser p on
    // t.projectinuser_id = p.projectinuser_id where p.project_id = :projectId ORDER
    // by task_createtime LIMIT 3;", nativeQuery = true)
    // public List<Task> findByTasksList(@Param("projectId") Long projectId);
    //
    public List<Task> findByProjectinuserId(Long projectinuser_id);

    public List<Task> findByProjectinuserIdAndTaskAccept(Long projectinuserId, Integer TaskAccept);

    public Optional<Task> findByTaskId(Long taskId);
}
