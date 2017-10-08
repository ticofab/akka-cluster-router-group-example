FROM hseeberger/scala-sbt

COPY build.sbt build.sbt

RUN sbt package

RUN sbt update

COPY src src

CMD sbt run
