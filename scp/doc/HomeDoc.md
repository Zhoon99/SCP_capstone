## GET /homeview/{userId}
### output
``` json
{
	"status": 200,
	"message": "message",
    "result":
    {
        "projects":
        [
            {
                "projectName": "String",
                "tasklist":
                [
                    {
                        "taskId": Long,
                        "projectinuserId": Long,
                        "taskContent": "String",
                        "taskOwner": "String",
                        "taskRequester": "string",
                        "taskComplete": INTEGER,
                        "taskAccept": INTEGER,
                        "taskRequesttime": "datetime",
                        "taskDeadline": "datetime",
                        "taskCreatetime": "datetime"
                    }
                ],
                "userCode": "String",
                "projectId": Long
            },
            {
                "projectName": "String",
                "tasklist": 
                [
                    {
                        "taskId": Long,
                        "projectinuserId": Long,
                        "taskContent": "String",
                        "taskOwner": "String",
                        "taskRequester": "string",
                        "taskComplete": INTEGER,
                        "taskAccept": INTEGER,
                        "taskRequesttime": "datetime",
                        "taskDeadline": "datetime",
                        "taskCreatetime": "datetime"
                    }
                ],
                "userCode": "String",
                "projectId": Long
            }
        ]
    }
}
```