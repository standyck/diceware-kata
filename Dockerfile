FROM clojure:lein

WORKDIR /usr/src/app

# Copy dependency information first
COPY project.clj ./
RUN lein deps

# Copy source code
COPY . .

# Build the uberjar with a predictable name
RUN lein uberjar && \
    mv target/uberjar/*-standalone.jar app-standalone.jar

ENTRYPOINT ["java", "-jar", "app-standalone.jar"]
CMD ["5"]
