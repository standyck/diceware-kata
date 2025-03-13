# Build stage
FROM clojure:lein as builder

WORKDIR /usr/src/app

# Copy dependency information first
COPY project.clj ./
RUN lein deps

# Copy source code
COPY . .

# Build the uberjar with a predictable name
RUN lein uberjar && \
    mv target/uberjar/*-standalone.jar app-standalone.jar

# Runtime stage
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app
COPY --from=builder /usr/src/app/app-standalone.jar ./

ENTRYPOINT ["java", "-jar", "app-standalone.jar"]
CMD ["5"]
