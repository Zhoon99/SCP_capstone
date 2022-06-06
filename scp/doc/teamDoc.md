# Home (팀 홈)

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

# Insert (팀 생성)

## Post "/team/insert"

### • input

``` json
{
    "teamId": null,
    "teamName": "String",
    "teamMembers": [
        {
            "userId": Long,
            "userNickname": null,
            "teaminuserCommoncode": "String",
            "teaminuserMaker": Integer
        },
        {
            "userId": Long,
            "userNickname": null,
            "teaminuserCommoncode": "String",
            "teaminuserMaker": Integer
        }
    ]
}
```

# Modify (팀 수정)

## Post "/team/modify"

### • input

``` json
{
    "teamId": Long,
    "teamName": "String",
    "teamMembers": [
        {
            "userId": Long,
            "userNickname": null,
            "teaminuserCommoncode": "String",
            "teaminuserMaker": null
        },
        {
            "userId": Long,
            "userNickname": null,
            "teaminuserCommoncode": "String",
            "teaminuserMaker": null
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
# 팀 수정 정보 가져오기

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

### • output

``` json
{
    "status": 201,
    "message": "message",
    "result": null
}
```
# 소속된 팀 목록 가져오기

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
# 팀에 속한 맴버 가져오기

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
#이메일로 맴버 검색

## Get "/team/getUsersByEmail/{userId}/{search}"

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




# 삭제 기능은 구현할 필요 없음

# 팀 삭제

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