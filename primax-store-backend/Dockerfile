FROM oracle/graalvm-ce:20.3.0-java17 as build

COPY . /project

RUN gu install native-image && \
    cd /project && \
    ./mvnw package -Pnative

FROM oracle/graalvm-ce:20.3.0-java17

COPY --from=build /project/target/primax-store-backend-*-runner /application

EXPOSE 8080

ENTRYPOINT ["/application"]

