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
<pre>
        Welcome to IRIS NativeAPI Global Editor

>>> serverIP [127.0.0.1]:
>>> serverPORT [1972]:
>>> namespace [USER]:
>>> username [_SYSTEM]:
>>> password [SYS]:

        Connected to Namespace USER on Server 127.0.0.1:1972

 0 = Select Global
 1 = Select Subscripts
 2 = Query Forward
 3 = Query Reverse
 4 = Show Global Tree
 5 = Update Global Value
 6 = Delete Global Value
 7 = Delete Global Tree
 * = Exit Demo
</pre>

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

