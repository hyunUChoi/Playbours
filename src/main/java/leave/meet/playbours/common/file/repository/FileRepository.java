package leave.meet.playbours.common.file.repository;

import leave.meet.playbours.common.file.dto.FileDto;

import java.util.List;

public interface FileRepository {

    void insert(FileDto dto);

    List<FileDto> findByFileName(String file);
}
