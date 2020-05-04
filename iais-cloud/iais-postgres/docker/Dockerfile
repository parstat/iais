FROM postgres:12.2
ENV POSTGRES_PASSWORD postgres
COPY sql/create-schemas.sql /docker-entrypoint-initdb.d/
