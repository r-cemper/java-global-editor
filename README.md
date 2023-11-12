## Prerequisites
Make sure you have [git](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git) and [Docker desktop](https://www.docker.com/products/docker-desktop) installed.

## Installation 
Clone/git pull the repo into any local directory
```
git https://github.com/rcemper/Dataset-OEX-reviews.git
```
Run the IRIS container with your project: 
```
docker-compose up -d --build && docker-compose logs -f
```
## How to Test it

````
docker -compose -exec iris java gedi
````

