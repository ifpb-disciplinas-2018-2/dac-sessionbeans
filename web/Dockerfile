#FROM payara/micro
#COPY /target/dac-sessionbean-web.war ${DEPLOY_DIR}
FROM payara/server-full
ENV APPNAME dac-sessionbean-web
ENV DOMAIN domain1
ENV DEPLOY ${PAYARA_PATH}/glassfish/domains/${DOMAIN}/autodeploy/
ENTRYPOINT $PAYARA_PATH/bin/asadmin start-domain --verbose ${DOMAIN} 
COPY target/$APPNAME.war ${DEPLOY}