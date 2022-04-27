package kr.mmgg.scp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.mmgg.scp.entity.ScpFile;

public interface ScpFileRepository extends JpaRepository<ScpFile, Long> {

}
