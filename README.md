#keeneye-backend

VM options
-Dspring.profiles.active=base,local

##Code Style

###IntelliJ IDEA:
1. Open File->Import Settings...
2. Choose file in project location named idea_settings.zip
3. Check "Code Style (schemes)" option
4. Press "OK" button.
5. Restart IDEA
5. Profit

##Run postgres DB in docker
1. Install Docker
2. In project root run command "docker-compose up --build"