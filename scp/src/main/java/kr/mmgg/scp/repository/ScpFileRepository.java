package kr.mmgg.scp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.mmgg.scp.entity.ScpFile;

public interface ScpFileRepository extends JpaRepository<ScpFile, Long> {
    public ScpFile findByFileId(Long fileId);

    public List<ScpFile> findByTaskId(Long taskId);
}
