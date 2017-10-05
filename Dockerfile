FROM hseeberger/scala-sbt

COPY build.sbt build.sbt

COPY src src

RUN sbt package

CMD sbt run
