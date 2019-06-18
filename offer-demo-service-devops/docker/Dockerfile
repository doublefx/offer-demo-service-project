FROM openjdk:8-jre-alpine

LABEL Name="offer-demo-service" \
      Description="Demo Offer Service" \
      Maintainer="Frederic Thomas<fthomas@apache.org>" \
      Vendor="Frederic Thomas" \
      License="Apache License Version 2.0"

ARG APPLICATION_NAME
ARG WORK_DIRECTORY=/opt/${APPLICATION_NAME}

WORKDIR ${WORK_DIRECTORY}

COPY ./${APPLICATION_NAME}.jar ${WORK_DIRECTORY}
COPY ./entrypoint.sh ${WORK_DIRECTORY}
COPY ./build_information.json ${WORK_DIRECTORY}

RUN mkdir ${WORK_DIRECTORY}/logs && \
    chmod +x ${WORK_DIRECTORY}/entrypoint.sh

ENTRYPOINT ["./entrypoint.sh"]
