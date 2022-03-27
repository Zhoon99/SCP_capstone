package kr.mmgg.scp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.mmgg.scp.entity.File;

public interface FileRepository extends JpaRepository<File, Long>{
	
}
