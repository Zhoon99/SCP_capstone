## Post "/project/crateproject"
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
[
    {
        "projectinuserId": Long,
        "user": null,
        "userId": Long,
        "project": null,
        "projectId": Long,
        "projectinuserCommoncode": String,
        "projectinuserMaker": Integer,
        "tasks":[]
    },
    {
        "projectinuserId": Long,
        "user": null,
        "userId": Long,
        "project": null,
        "projectId": Long,
        "projectinuserCommoncode": String,
        "projectinuserMaker": Integer,
        "tasks":[]
    }
]
```
## GET /alltask/{projectId}
### output
``` json
[
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
			}
		]
	}
]
```
## GET /mytask/{userId}/{projectId}
### output
``` json
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
		}
	]
}
```
## POST /sendtask
### input
``` json
{
  "userId": Long,
  "tasklist" :
  [
  	{
      	"taskId" : null,
  		"projectinuserId": Long,
  		"taskRequester" : String,
  		"taskOwner" : String,
  		"taskContent" : String,
  		"taskComplete" : boolean(Integer),
  		"taskAccept" : boolean(Integer),
  		"taskRequesttime" : datetime,
  		"taskDeadline" : datetime,
  		"taskCreatetime" : datetime
	}
  ]  
}
```
## GET /receivetask/{projectId}/{projectinuserId}
### output
``` json
[
	{
		"task":
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
	}
]
```