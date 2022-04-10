# Home

## Get "/team/home/{userId}"

### • output

``` json
{
	"status": 200,
	"message": "message",
    "result":
    {
        "teamHome": {
            "myTeams": [
                {
                    "teamId": Long,
                    "teamName": "String",
                    "teamMembers": [
                        {
                            "userId": Long,
                            "userNickname": "String",
                            "teaminuserCommoncode": "String",
                            "teaminuserMaker": Integer
                        },
                        {
                            "userId": Long,
                            "userNickname": "String",
                            "teaminuserCommoncode": "String",
                            "teaminuserMaker": Integer
                        }
                    ]
                },
                {
                    "teamId": Long,
                    "teamName": "String",
                    "teamMembers": [
                        {
                            "userId": Long,
                            "userNickname": "String",
                            "teaminuserCommoncode": "String",
                            "teaminuserMaker": Integer
                        },
                        {
                            "userId": Long,
                            "userNickname": "String",
                            "teaminuserCommoncode": "String",
                            "teaminuserMaker": Integer
                        }
                    ]
                }
            ],
            "sharedTeams": [
                {
                    "teamId": Long,
                    "teamName": "String",
                    "teamMembers": [
                        {
                            "userId": Long,
                            "userNickname": "String",
                            "teaminuserCommoncode": "String",
                            "teaminuserMaker": Integer
                        },
                        {
                            "userId": Long,
                            "userNickname": "String",
                            "teaminuserCommoncode": "String",
                            "teaminuserMaker": Integer
                        }
                    ]
                },
                {
                    "teamId": Long,
                    "teamName": "String",
                    "teamMembers": [
                        {
                            "userId": Long,
                            "userNickname": "String",
                            "teaminuserCommoncode": "String",
                            "teaminuserMaker": Integer
                        },
                        {
                            "userId": Long,
                            "userNickname": "String",
                            "teaminuserCommoncode": "String",
                            "teaminuserMaker": Integer
                        }
                    ]
                }
            ]
        }
    }
}
```

# Insert

## Post "/team/insert"

### • input

``` json
{
    "teamId": null,
    "teamName": "String",
    "teamMembers": [
        {
            "userId": Long,
            "userNickname": "String",
            "teaminuserCommoncode": "String",
            "teaminuserMaker": Integer
        },
        {
            "userId": Long,
            "userNickname": "String",
            "teaminuserCommoncode": "String",
            "teaminuserMaker": Integer
        }
    ]
}
```

### • output

``` json
{
    "status": 201,
    "message": "message",
    "result": null
}
```

## Get "/team/getUserTeamList/{userId}"

### • output

``` json
{
    "status": 200,
    "message": "message",
    "result": {
        "teams": [
            {
                "teamId": Long,
                "teamName": "String"
            },
            {
                "teamId": Long,
                "teamName": "String"
            }
        ]
    }
}
```

## Get "/team/getTeamMembers/{teamId}"

### • output

``` json
{
    "status": 200,
    "message": "message",
    "result": {
        "members": [
            {
                "userId": Long,
                "userNickname": "String",
                "teaminuserCommoncode": "String",
                "teaminuserMaker": Integer
            },
            {
                "userId": Long,
                "userNickname": "String",
                "teaminuserCommoncode": "String",
                "teaminuserMaker": Integer
            }
        ]
    }
}
```

## Get "/team/getUsersByEmail/{search}"

### • output

``` json
{
    "status": 200,
    "message": "message",
    "result": {
        "emailUser": [
            {
                "userId": Long,
                "userNickname": "String",
                "userEmail": "String"
            },
            {
                "userId": Long,
                "userNickname": "String",
                "userEmail": "String"
            }
        ]
    }
}
```

## Post "/team/deleteTeamMember"

### • input

``` json
{
    "teamId" : Long,
    "userId" : Long
}
```

### • output

``` json
{
    "status": 203,
    "message": "message",
    "result": null
}
```

# Modify

## Post "/team/modify"

### • input

``` json
{
    "teamId": Long,
    "teamName": "String",
    "teamMembers": [
        {
            "userId": Long,
            "userNickname": "String",
            "teaminuserCommoncode": "String",
            "teaminuserMaker": Integer
        },
        {
            "userId": Long,
            "userNickname": "String",
            "teaminuserCommoncode": "String",
            "teaminuserMaker": Integer
        }
    ]
}
```

### • output

``` json
{
    "status": 201,
    "message": "message",
    "result": null
}
```

## Get "/team/getTeamModifyInfo/{teamId}"

### • output

``` json
{
    "status": 200,
    "message": "message",
    "result": {
        "modifyTeamInfo": {
            "teamId": Long,
            "teamName": "String",
            "teamMembers": [
                {
                    "userId": Long,
                    "userNickname": "String",
                    "teaminuserCommoncode": "String",
                    "teaminuserMaker": Integer
                },
                {
                    "userId": Long,
                    "userNickname": "String",
                    "teaminuserCommoncode": "String",
                    "teaminuserMaker": Integer
                }
            ]
        }
    }
}
```

# Delete

## Get "/team/getUserTeamList/{userId}"

### • output

``` json
{
    "status": 203,
    "message": "message",
    "result": null
}
```