#How to user rest api


###start rest application:
cd rest-app/target

java -jar rest-app.jar

##Show product version:

curl http://localhost:8088


##How to work with users

Create test users: curl http://localhost:8088/createTestUsers

Show all users: curl http://localhost:8088/users

Create user: curl -H 'Content-Type: application/json' --data '{"id":int,"user_name":"[username]@user.com","user_pass":"String", "is_admin":int}' http://localhost:8088/create-user

Show user basket: curl -H 'Content-Type: application/json' -- data '{"id":int,"user_name":"[username]@user.com","user_pass":"String", "is_admin":int}' http://localhost:8088/Basket/

Buy tour: curl -H 'Content-Type: application/json' --data '{"user_id":[int user_id], "tour_id":[int tour_id]}' http://localhost:8088/buy-tour

##How to work with tours

Create test tours: curl http://localhost:8088/createTestTours

Create tour: curl -H 'Content-Type: application/json' --data '{"id":int, "direction":"String", "date":"yyyy-mm-dd", "coast":int}' http://localhost:8088/createTour

Update tour: curl -X 'PUT' -H "Content-Type: application/json" -d '{"id":[int tour_id], "direction":"String", "date":"yyyy-mm-dd", "coast":[int coast]}' http://localhost:8088/updateTour

Delete tour: curl -X 'DELETE' -d '{"id":"int tour_id"}' http://localhost:8088/deleteTour/tour_id

Search tour: curl http://localhost:8088/Search/tour_direction

Find all tours: curl http://localhost:8088/ShowAllTours


 




