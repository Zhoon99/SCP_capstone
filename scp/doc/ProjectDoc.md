## Post "/project/crateproject"
### input
```
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
```
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
```
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
```
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
```
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
```
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