# Unit2-Project

## Gamer Library
![Logo](https://user-images.githubusercontent.com/89564513/148089103-de9f071a-88a2-4a2e-88be-921b4823cfa2.png)

## About

Game Library/Wishlist API that a gamer can use to add, update, or delete games from their own individual library.

## User Stories
![User stories banner](https://user-images.githubusercontent.com/89564513/148093074-98f7b9c1-9b10-4f5f-a4d9-e65a03a82b2b.png)

**Game**<br>
  -As a gamer, I can create a game in my library.<br>
  -As a gamer, I can update a game in my library.<br>
  -As a gamer, I can read a game in my library.<br>
  -As a gamer, I can delete a game from my library.<br>

**Genre**<br>
  -As a gamer, I can create a genre in my library.<br>
  -As a gamer, I can update a genre in my library.<br>
  -As a gamer, I can read a genre in my library.<br>
  -As a gamer, I can read a game from a genre in my library.<br>
  -As a gamer, I can delete a genre from my library.<br>

**Developer**<br>
  -As a gamer, I can create a developer in my library.<br>
  -As a gamer, I can update a developer in my library.<br>
  -As a gamer, I can read a developer in my library.<br>
  -As a gamer, I can delete a developer from my library.<br>

**Publisher**<br>
  -As a gamer, I can create a publisher in my library.<br>
  -As a gamer, I can update a publisher in my library.<br>
  -As a gamer, I can read a publisher in my library.<br>
  -As a gamer, I can read developers from a publisher in my library.<br>
  -As a gamer, I can delete a publisher from my library.<br>

![MVP Banner](https://user-images.githubusercontent.com/89564513/148093144-0b0d1fb4-ac11-4f79-92fa-12e1da1d3cbe.png)<br>

We want to build a working database with four models: Game, Genre, Developer, and Publisher. The Game model will be containing our four CRUD endpoints. The user will be notified when invalid requests are made.

![Screenshot (19)](https://user-images.githubusercontent.com/89564513/148093922-3091cfc8-b01c-4122-9ef1-4cadbfea3f3a.png)

| Day 1 | Day 2 | Day 3 | Day 4 | Day 5 |
|-------|-------|-------|-------|-------|
| Project description| Models| Continuing CRUD| Clean Up Code| Continue presentation notes
 Userstories| CRUD operations| Exception Handeling| Presentation Notes
 ERD |Debugging| 
 Endpoints| MVP(?)|
 Set up Repo|
 
## /EndPoints

| Request Type | Url  | Action | Request Header | Access | Request Body|
|--------------|------|--------|----------------|--------|-------------|
|GET | /game|Get all games|None|Public| |
|GET| /game{gameid}|Get single game|None|Public| |
|POST| /publisher/{publisherid}/developer/{debveloperid}/genre/{genreid}/game|Create single game|None|Public| | 
|PUT| /game/{gameid}|Update single game|None|Public||
|DELETE| /game/{gameid}| Delete single game|None|Public||
|GET| /developer|Get all developers|None|Public||
|GET| /developer/{developerid}|Get single developer|None|Public||
|GET| /developer/{developerid}/games|Get all games created by a single developer|None|Public||
|GET| /developer/{developerid}/games/{gameid}|Get single game single developer|None|Public||
|POST| /publisher/{publisherid}developer|Create single developer|None|Public||
|PUT| /developer/{developerid}|Update single developer|None|Public||
|DELETE| /developer/{developerid}|Delete single developer|None|Public||
|GET| /publisher|Get all publishers|None|Public||
|GET| /publisher/{publisherid}|Get single publisher|None|Public||
|POST| /publisher|Create single publisher|None|Public||
|PUT| /publisher/{publisherid}|Update single publisher|None|Public||
|DELETE| /publisher/{publisherid}|Delete single publisher|None|Public||
|GET| /genre|Get all genres|None|Public||
|GET| /genre/{genreid}|Get single genre|None|Public||
|Get| /genre/{genreid}/game|Get all games within a single genre|None|Public||
|POST| /genre|Create a genre|None|Public||
|PUT| /genre/{genreid}|Update a genre|None|Public||
|DELETE| /genre/{genreid}|Delete a genre|None|Public||
