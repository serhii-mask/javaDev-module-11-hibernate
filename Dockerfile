FROM postgres
ENV POSTGRES_USER admin
ENV POSTGRES_PASSWORD 12345
EXPOSE 33300:5432
ENV POSTGRES_DB starWars

# docker build -t postgres:v1 .
# docker run --name hw-10-hibernate -d -p 33300:5432 postgres:v1

# docker build -t postgres[name image in Docker]:v1[name tag in image] .
# docker run --name hw-10-hibernate[name your container] -d -p 33300:5432 postgres:v1[name your image]
