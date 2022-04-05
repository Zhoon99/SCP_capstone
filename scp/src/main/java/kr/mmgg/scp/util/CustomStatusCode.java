package kr.mmgg.scp.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CustomStatusCode {

    LOOKUP_SUCCESS(200, "조회 완료"),
    CREATE_SUCCESS(201, "생성 완료"),
    MODIFY_SUCCESS(202, "수정 완료"),
    DELETE_SUCCESS(203, "삭제 완료"),
    ;

    private final int status;
    private final String message;
}
