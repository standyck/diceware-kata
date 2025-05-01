# Stage 1: Build the application
FROM clojure:openjdk-8-lein AS builder

WORKDIR /app

# Copy only the files needed for dependency resolution first
COPY project.clj /app/

# Download dependencies
RUN lein deps

# Copy the rest of the application
COPY . /app/

# Build the uberjar
RUN lein uberjar

# Stage 2: Create a minimal runtime image
FROM eclipse-temurin:8-jre-alpine

WORKDIR /app

# Copy only the uberjar from the builder stage
COPY --from=builder /app/target/uberjar/diceware-0.1.0-SNAPSHOT-standalone.jar /app/diceware.jar

# Set the entrypoint
ENTRYPOINT ["java", "-jar", "/app/diceware.jar"]
