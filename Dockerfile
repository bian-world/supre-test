FROM metersphere/fabric8-java-alpine-openjdk11-jre

#RUN echo -e http://mirrors.ustc.edu.cn/alpine/v3.9/main/ > /etc/apk/repositories && \
#    apk --no-cache add tzdata  && \
#    ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && \
#    echo "Asia/Shanghai" > /etc/timezone


RUN sed -i 's/dl-cdn.alpinelinux.org/mirrors.tuna.tsinghua.edu.cn/g' /etc/apk/repositories
# RUN apk add --no-cache gcc musl-dev linux-headers

RUN apk update && apk add --no-cache bash \
        tzdata \
        alsa-lib \
        at-spi2-atk \
        atk \
        cairo \
        cups-libs \
        dbus-libs \
        eudev-libs \
        expat \
        flac \
        gdk-pixbuf \
        glib \
        libgcc \
        libjpeg-turbo \
        libpng \
        libwebp \
        libx11 \
        libxcomposite \
        libxdamage \
        libxext \
        libxfixes \
        tzdata \
        libexif \
        udev \
        xvfb \
        zlib-dev \
        chromium    \
        chromium-chromedriver && \
        ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && \
        echo "Asia/Shanghai" > /etc/timezone
        
RUN apk add --no-cache tini


#ENV JVM_OPTS -server -Xms1g -Xmx1g -XX:+UseG1GC
#ENV JAVA_OPTS -Dfile.encoding=UTF-8 -Djava.security.egd=file:/dev/urandom  -Dspring.profiles.active=prod 
#ENV JAVA_AGENT=

# RUN mkdir jacoco
COPY backend/src/main/resources/jmeter/bin  /opt/jmeter/bin
COPY backend/src/main/resources/font  /usr/share/fonts/chinese
# ADD . /opt/supretest/
# ADD ./backend /jacoco/

ADD backend/target/backend-1.0.jar /app.jar
RUN ls 
#ADD backend/src/main/resources/jacoco/org.jacoco.agent-0.8.10-runtime.jar  /jacoco_agent.jar

ENTRYPOINT ["/sbin/tini", "--"]
CMD ["/bin/sh", "-c", "java -jar /app.jar"]

#ENTRYPOINT ["/bin/sh", "-c", "java  -javaagent:\"/jacoco_agent.jar=destfile=jacoco.exec,dumponexit=false,output=tcpclient,port=6300,address=100.100.156.152,sessionid=supretest,includes=com.supretest.api.service.*\" -jar /app.jar"]

#mv /temp/* /jacoco/ &&