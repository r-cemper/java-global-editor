[![Quality Gate Status](https://community.objectscriptquality.com/api/project_badges/measure?project=intersystems_iris_community%2Fjava-global-editor&metric=alert_status)](https://community.objectscriptquality.com/dashboard?id=intersystems_iris_community%2Fjava-global-editor)

As another contribution to the Java Contest, I decided to present a   
CRUD++ Global Editor based on IRIS Native API for Java.  
++ because it's a little bit more than just **C**reate, **R**ead, **U**pdate, **D**elete  
Visualization of a global is always important to review results   
immediately. For this purpose, I have extended the API with a Tree Viewer     
aka. ZWrite and $Query Style Navigator Forward and Reverse operating  
also at any subtree level.
Finally, an option to ZKill to delete the content of a Global Node   
without deleting the subtree below. This required a small helper   
class on the server side as an extension to the default API.   
![image](https://github.com/rcemper/java-global-editor/assets/146277387/a8851c94-823b-4f13-861f-1189b065abc6)

## Prerequisites
Make sure you have [git](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git) and [Docker desktop](https://www.docker.com/products/docker-desktop) installed.

## Installation 
Clone/git pull the repo into any local directory
````
git https://github.com/rcemper/java-global-editor.git
````
Run the IRIS container with your project: 
````
docker-compose up -d --build && docker-compose logs -f
````
## How to Test it
````
docker-compose exec iris java gedi
````
If you prefer to test with **WebTerminal** type
````
do ^javademo
````
**HINT:**
This runs in two asynchronous processes. If you miss some output  
just enter **?** at the prompt to repaint the last display.

<img widht="600" src="https://github.com/rcemper/java-global-editor/assets/146277387/16b4e6ee-dc61-4b11-972a-69459beedd5f">


[Article in DC](https://community.intersystems.com/post/java-global-editor)

[Video](https://youtu.be/FE4MMGFkp4A)    

[Demo Server SMP](https://ava-global-editor.demo.community.intersystems.com/csp/sys/UtilHome.csp)    
[Demo Server WebTerminal](https://ava-global-editor.community.intersystems.com/terminal/)     

