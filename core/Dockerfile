#FROM payara/micro
#COPY /target/dac-sessionbean.war ${DEPLOY_DIR}

FROM payara/server-full
ENV APPNAME dac-sessionbean-core
ENV DOMAIN domain1
ENV DEPLOY ${PAYARA_PATH}/glassfish/domains/${DOMAIN}/autodeploy/
#RUN echo 'AS_ADMIN_PASSWORD=admin\n\ EOF\n' >> /opt/pwdfile
#RUN $PAYARA_PATH/bin/asadmin start-domain ${DOMAIN} && \
#    $PAYARA_PATH/bin/asadmin --user admin --passwordfile=/opt/pwdfile enable-secure-admin
ENTRYPOINT $PAYARA_PATH/bin/asadmin start-domain --verbose ${DOMAIN} 
#&& \
#    $PAYARA_PATH/bin/asadmin --user admin --passwordfile=/opt/pwdfile 
COPY target/$APPNAME.war  ${DEPLOY}
EXPOSE 4848 8009 9009 8080 8181 3700
#RUN echo 'AS_ADMIN_PASSWORD=admin\n\ EOF\n' >> /opt/pwdfile
#RUN $PAYARA_PATH/bin/asadmin start-domain ${DOMAIN} && \
#    $PAYARA_PATH/bin/asadmin --user admin --passwordfile=/opt/pwdfile enable-secure-admin
#ADD target/$APPNAME/WEB-INF/lib/ /opt/payara41/glassfish/domains/${DOMAIN}/lib/

#FROM jboss/wildfly
#ENV DEPLOY /opt/jboss/wildfly/standalone/deployments/
#ADD target/singleton.war ${DEPLOY}
#CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-c", "standalone.xml", "-b", "0.0.0.0"]
#RUN /opt/jboss/wildfly/bin/add-user.sh admin Admin#007 --silent
#CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]
#RUN /opt/jboss/wildfly/bin/add-user.sh -a ricardojob job.2016 --silent
#CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-c", "standalone.xml", "-b", "0.0.0.0"]



