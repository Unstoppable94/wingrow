 

curl  -X POST -H "Content-Type:application/json" http://localhost:8080/cicdweb/webapi/projectestAddOrModifyMavenProject --data-binary @project.json


curl  -X PUT -H "Content-Type:application/json" http://localhost:8080/cicdweb/webapi/project/ --data-binary @project.json



curl  -X POST -H "Content-Type:application/json" http://localhost:8080/cicdweb/webapi/projectgroup/test2 --data-binary @test2.json

curl  -X DELETE -H "Content-Type:application/json" http://localhost:8080/cicdweb/webapi/projectgroup/test2 --data-binary @test2.json



curl  -X DELETE -H "Content-Type:application/json" http://10.211.55.6:18080/cicdweb/webapi/project/test --data-binary @project.json


curl  -X PUT -H "Content-Type:application/json" http://10.211.55.6:18080/cicdweb/webapi/project/%B2%E2%CA%D4 --data-binary @project.json


curl  -X POST -H "Content-Type:application/json" http://localhost:8080/cicdweb/webapi/project/ --data-binary @project.json

curl -X POST -H "Content-Type:application/json" 
http://10.211.55.6:28080/createItem?name=pro1492011234004 --data-binary @project.json


curl -X POST -H "$CRUMB" http://jenkins:jenkins@10.211.55.6:28080/createItem?name=maskio2  --data-binary  "@p.xml"-H "Content-Type:text/xml"


CRUMB=$(curl -s 'http://jenkins:jenkins@10.211.55.6:28080/crumbIssuer/api/xml?xpath=concat(//crumbRequestField,":",//crumb)')



curl -X POST -H "$CRUMB" http://10.211.55.6:8080/createItem?name=maskio2  --data-binary  "@p.xml"-H "Content-Type:text/xml"
