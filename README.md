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
|POST| /game/|Create single game|None|Public| | 
|PUT| /game/{gameid}|Update single game|None|Public||
|DELETE| /game/{gameid}| Delete single game|None|Public||
|GET| /game/{gameId}/developer/|Get all developers|None|Public||
|GET| /game/{gameId}/developer/{developerId}|Get single developer|None|Public||
|POST| game/{gameId}/developer/|Create single developer|None|Public||
|PUT| /game/{gameId}/developer/{developerId}/|Update single developer|None|Public||
|DELETE| /game/{gameId}/developer/{developerId}|Delete single developer|None|Public||
|GET| /game/{gameId}/publisher|Get all publishers|None|Public||
|GET| /game/{gameId}/publisher/{publisherId}|Get single publisher|None|Public||
|POST| /game/{gameId}/publisher|Create single publisher|None|Public||
|PUT| /game/{gameId}/publisher/{publisherId}|Update single publisher|None|Public||
|DELETE| /game/{gameId}/publisher/{publisherId}|Delete single publisher|None|Public||
|GET| /game/{gameId}/genre|Get all genres|None|Public||
|GET| /game/{gameId}/genre/{genreId}|Get single genre|None|Public||
|POST| /game/{gameId}/genre|Create a genre|None|Public||
|PUT| /game/{gameId}/genre/{genreId}|Update a genre|None|Public||
|DELETE| /game/{gameId}/genre/{genreId}|Delete a genre|None|Public||

## ERD
![ERD](https://user-images.githubusercontent.com/89564513/148717801-a844bacf-f929-4000-b1b7-7c959173c417.jpeg)


## The Process
![The Process](https://user-images.githubusercontent.com/89564513/148101651-29730772-c08e-40aa-b7ac-b798a3ff510b.png)

1. First, We decided on an app, that would effeciently display our knowledge of Restful API's and CRUD opperations.
  -We decided on a Game library, for this app, we decided on four models for our app; *Game*, *Developer*, *Publisher*, and *Genre*.<br>
  -From there we developed our ERD, and listed out our endpoints. <br>
2. We developed our User Stories and came up with a schedule to ensure that all requirements were met, and that we managed our time wisely.<br>
  -To make things simple, we set up our Repository and built the skeloton of our app.<br>
  -We also communicated clearly on how we wanted to use git to mangage the project, to ensure we didn't have any hiccups.<br>
3. We split the work, and created each model needed and their respective service and controller classes.<br>
  -Added the necessary depedacies to the POM.xl file.
  -While doing so, we included exception handeling as well.<br>
4. We ran into the issue of of endpoints not quite giving us the results we were expecting. To solve this, istead of having four seprate controllers to handle each model, we went with the *Game Controller* & *Game Service* handleing all of our CRUD methods.
5. Ended up adding the ability to have a gamer register and login to their database using JSON web Tokens. <br>
  -Tested endpoints and achieved MVP! <br>
  
  
## Resources & Technologies

1. IntelliJ 
2. Spring Boot
3. Maven
4. postgres
5. PgAdmin
6. JWT
7. Postman (Testing endpoints)
8. Canva (for ReadMe graphics)
9. LucidChart - ERD

## Installation 

1. Fork and Clone the Repo
2. Create a Database called *project2*
3. Open project and change application dev properties to reflect your username and password in Pgadmin, change to the correct port number
4. Run the application and open the Url

