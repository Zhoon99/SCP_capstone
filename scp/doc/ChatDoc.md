# Get "/chat/{chatroomId}"
##채팅방 화면
### output

```json
{
    "status": 200,
    "message": "조회 완료",
    "result": {
        "messages": {
            "chatroomName": "String",
            "chatroomLeaderId": Long,
            "messageList": [
                {
                    "userId": Long,
                    "userNickname": "String",
                    "messageContent": "String",
                    "messageTime": "String"
                },
                {
                    "userId": Long,
                    "userNickname": "String",
                    "messageContent": "String",
                    "messageTime": "String"
                }
            ]
        }
    }
}
```
# GET /createRoom
## 채팅방 생성
### input
``` json
{
   	"chatroomName": String,
  	"userId":Long,
    "chatroomMember": 
    [
      	{
        	"userId": Long
      	},
      	{
        	"userId": Long
      	}
    ]
}
```
###output
``` json
{
	"status": 200,
	"message": "생성 완료",
	"result": null
}
```
# GET /modifyRoom/{chatroomId}
## 채팅방 수정 (멤버 추가)
### input
``` json
{
   	"chatroomName": String,
    "chatroomMember": 
    [
      	{
        	"userId": Long
      	},
      	{
        	"userId": Long
      	}
    ]
}
```
# GET /exitChatroom/{chatroomId}/{userId}
## 채팅방 수정 (멤버 삭제)
###output
``` json
{
	"status": 200,
	"message": "수정 완료",
	"result": null
}
```
###output
``` json
{
	"status": 200,
	"message": "수정 완료",
	"result": null
}
```


# GET /deleteRoom/{chatroomId}
## 채팅방 수정 (멤버 추가)
### output
``` json
{
	"status": 200,
	"message": "삭제 완료",
	"result": null
}
```

# GET /lookupRoom/{userId}
## 채팅방 조회
### output
``` json
{
"status": 200,
"message": "조회 완료",
"result":{
	"room":[
			{
				"chatroomId": Long,
				"chatroomName": String,
				"chatroomCommoncode": String,
				"headCount": Integer
			}
		]
	}
}
```
# GET /lookupMember/{email}
## 이메일로 멤버 조회
### output
``` json
{
"status": 200,
"message": "조회 완료",
"result":{
	emailUser":
	[
				{
					"userId": Long,
					"userNickname": String,
					"userEmail": String
				}
			]
		}
	}
```
# GET /exitChatroom/{chatroomId}/{userId}
## 채팅방 나가기 (버튼)
### output
``` json
{
	"status": 200,
	"message": "삭제 완료",
	"result": null
}
```