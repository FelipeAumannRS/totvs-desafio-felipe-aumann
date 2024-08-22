FROM ubuntu:latest
LABEL authors="felip"

ENTRYPOINT ["top", "-b"]