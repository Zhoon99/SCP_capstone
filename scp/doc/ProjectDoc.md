# Post "/project/createproject"
## SCP 300 프로젝트 추가 / 수정
### input
``` json
{
    "title": "String",
    "member": 
    [
        {
        	"userId":Long,
        	"projectinuserMaker":Integer,
        	"projectinuserCommoncode":"String"
        },
        {
        	"userId":Long,
        	"projectinuserMaker":Integer,
        	"projectinuserCommoncode":"String"
        }
    ] 
}
```

### output
``` json
{
	"status": 200,
	"message": "message"
}
```

# GET /alltask/{projectId}
## SCP-301 프로젝트 상세 - 전체 할일
### output
``` json
{
	"status": 200,
	"message": "message",
	"result":
	{
		"tasklist":
		[
			{
				"taskId": Long,
				"projectinuserId": Long,
				"taskContent": String,
				"taskOwner": String,
				"taskRequester": String,
				"taskComplete": boolean(Integer),
				"taskAccept": boolean(Integer),
				"taskRequesttime": datetime,
				"taskDeadline": datetime,
				"taskCreatetime": datetime
			},
			{
				"taskId": Long,
				"projectinuserId": Long,
				"taskContent": String,
				"taskOwner": String,
				"taskRequester": String,
				"taskComplete": boolean(Integer),
				"taskAccept": boolean(Integer),
				"taskRequesttime": datetime,
				"taskDeadline": datetime,
				"taskCreatetime": datetime
			}
		]
	}
}
```
# GET /mytask/{userId}/{projectId}
## SCP-302 프로젝트 상세 - 내 할일
### output
``` json
{
	"status": 200,
	"message": "message",
	"result" :
	{
		"taskList":
		[
			{
				"taskId": Long,
				"projectinuserId": Long,
				"taskContent": String,
				"taskOwner": String,
				"taskRequester": String,
				"taskComplete": boolean(Integer),
				"taskAccept": boolean(Integer),
				"taskRequesttime": datetime,
				"taskDeadline": datetime,
				"taskCreatetime": datetime
			},
			{
				"taskId": Long,
				"projectinuserId": Long,
				"taskContent": String,
				"taskOwner": String,
				"taskRequester": String,
				"taskComplete": boolean(Integer),
				"taskAccept": boolean(Integer),
				"taskRequesttime": datetime,
				"taskDeadline": datetime,
				"taskCreatetime": datetime
			}

		]
	}
}
```
# Patch /whethertask/{userId}/{taskId}
### output
``` json
{
	"status": 200,
	"message": "message",
}
```
# GET /receivetask/{projectId}/{projectinuserId}
## SCP-303 프로젝트 상세 - 받은 요청
### output
``` json
{
	"status": 200,
	"message": "message",
	"result" :
	{
		"taskList":
		[
			{
				"taskId": Long
				"projectinuserId": Long,
				"taskContent": String
				"taskOwner": String,
				"taskRequester": String,
				"taskComplete": boolean(Integer),
				"taskAccept": boolean(Integer),
				"taskRequesttime": datetime,
				"taskDeadline": datetime,
				"taskCreatetime": datetime
			},
			{
				"taskId": Long
				"projectinuserId": Long,
				"taskContent": String
				"taskOwner": String,
				"taskRequester": String,
				"taskComplete": boolean(Integer),
				"taskAccept": boolean(Integer),
				"taskRequesttime": datetime,
				"taskDeadline": datetime,
				"taskCreatetime": datetime
			}
		]
	}
}
```
# PATCH /receivetask/{taskId}/{selected} 
## SCP-303 프로젝트 상세 - 받은 요청 // 수락 - 거절 (1, -1) 1은 수락 -1 거절 0은 요청을 받은상태
### output
``` json
	"status": 200,
	"message": "message"
```

# GET /requestask/{projectId}/{userId}
## SCP-304 프로젝트 상세 - 보낸 요청 //
### output
``` json
{
	"status": 200,
	"message": "message",
	"result":
	{
		"taskList":
		[
			{
				"taskId": Long
				"projectinuserId": Long,
				"taskContent": String
				"taskOwner": String,
				"taskRequester": String,
				"taskComplete": boolean(Integer),
				"taskAccept": boolean(Integer),
				"taskRequesttime": datetime,
				"taskDeadline": datetime,
				"taskCreatetime": datetime
			},
			{
				"taskId": Long
				"projectinuserId": Long,
				"taskContent": String
				"taskOwner": String,
				"taskRequester": String,
				"taskComplete": boolean(Integer),
				"taskAccept": boolean(Integer),
				"taskRequesttime": datetime,
				"taskDeadline": datetime,
				"taskCreatetime": datetime
			}
		]
	}
}
```
# GET /sendtask/{projectId}
## SCP-305 프로젝트 상세 요청 멤버 추가 
### output
``` json
{
	"status": 200,
	"message": "message",
	"result":
	{
		"userList":
		[
			{
				"id": Long,
				"userNickname": "String",
				"userEmail": "String",
				"userSnstype": "String",
				"userRole": "String"
			},
			{
				"id": Long,
				"userNickname": "String",
				"userEmail": "String",
				"userSnstype": "String",
				"userRole": "String"
			}
		]
	}
}
```
# POST /sendtask
## SCP-305 프로젝트 상세 할일 작성
### input
``` json
		{
	    	"userId" : Long,
			"projectinuserId": Long,
			"taskContent" : String,
			"taskDeadline" : datetime,
		}
```
### ouput
``` json 
{
	"status": 200,
	"message": "message"
}
```
# POST /commentwrite
## 댓글 작성
### input 
``` JSON
	{
		"taskId" : Long
		"userId" : Long
		"commentContent" : String
	}
```
### ouput
``` JSON 
{
	"status": 201,
	"message": "message"
}
```
# PATCH /commentmodify/{commentId}
## 댓글 수정
### input
``` JSON
{
  "commentId" : Long,
  "commentModifyTime" : DateTime(String)
  "commentContent" : String
}
```
###output
``` JSON 
{
	"status": 202,
	"message": "message"
}
```
# DELETE /commentdelete/{commentId}
## 댓글 삭제
### ouput
``` JSON 
{
	"status": 203,
	"message": "message"
}
```
# GET /taskDetail/{taskId}
## SCP-306 할 일 상세
### output
``` JSON
{
	"status": 200,
	"message": "message",
	"result":{
			"taskDetail" : {
				"taskId" : Long,
				"owner_userName" : String,
				"requester_userName" : String,
				"taskContent" : String,
				"taskDeadline" : String,
				"commentList" : [
						{
							"commentId" : Long,
							"taskId" : Long,
							"userName" : String,
							"commentTime" : DateTime(String),
							"commentContent" : String
						},
						{
							"commentId" : Long,
							"taskId" : Long,
							"userName" : String,
							"commentTime" : DateTime(String),
							"commentContent" : String
						}
					]
				}
			}
		}
```
