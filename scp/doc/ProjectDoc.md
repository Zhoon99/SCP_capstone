# Post "/project/crateproject"
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
### output
``` json
	"status": 200,
	"message": "message"
```

# GET /requestask/{projectId}/{userId}
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
### input
``` json
		{
	    	"taskId" : null,
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
### ouput
``` JSON 
{
	"status": 203,
	"message": "message"
}
```
# GET /taskDetail/{taskId}
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
