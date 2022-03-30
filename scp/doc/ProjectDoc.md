##Post "/project/crateproject"
###input
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

###output
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
##GET /alltask/{projectId}
###output
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
##GET /mytask/{userId}/{projectId}
###output
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
##test /sendTask
```
{
  "projectName" : "프로젝트1",
  "projectId" : 1,
  "tasklist" :
  [
  	{
      	"taskId" : null,
  		"projectinuserId": 5,
  		"taskRequester" : "공연성(김기태교수님소속)",
  		"taskOwner" : "김기태교수님",
  		"taskContent" : "테스트입니다.",
  		"taskComplete" : 0,
  		"taskAccept" : 0,
  		"taskRequesttime" : "2022-03-28 08:00:00",
  		"taskDeadline" : "2022-03-28 08:00:00",
  		"taskCreatetime" : "2022-03-28 08:00:00"
	}
  ]  
}
```