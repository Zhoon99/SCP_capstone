package kr.mmgg.scp.util;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    /* 400 BAD_REQUEST : 잘못된 요청 */

    /* 401 UNAUTHORIZED : 인증되지 않은 사용자 */
    /* 404 NOT_FOUND : Resource 를 찾을 수 없음 */
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 유저 정보를 찾을 수 없습니다"),
    PROJECT_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 프로젝트를 찾을 수 없습니다."),
    PROJECT_IN_USER_NOT_FOUND(HttpStatus.NOT_FOUND, "프로젝트 안에 해당 유저를 찾을 수 없습니다."),
    PROJECT_OR_USER_NOT_FOUND(HttpStatus.NOT_FOUND, "유저나 프로젝트를 찾을 수 없습니다."),
    TASK_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 할일을 찾을 수 없습니다."),
    TASK_NOT_MATCH(HttpStatus.NOT_FOUND, "해당 할일과 유저가 매칭되지 않습니다."),
    TEAM_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 팀을 찾을 수 없습니다."),
    PAGE_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 페이지가 없습니다."),
    COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND,"해당 댓글을 찾을 수 없습니다."),
    /* 500 Internal Server Error 서버가 처리 방법을 모르는 상황이 발생했습니다. */
    INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버에러"),
    CHATROOM_NOT_FOUND(HttpStatus.NOT_FOUND,"해당 채팅방을 찾을 수 없습니다."),
    ;

    private final HttpStatus httpStatus;
    private final String detail;
}
