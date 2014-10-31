##### to have hawtio integration you have to install this plugin:
    install -s mvn:io.hawt/hawtio-plugin-redhat-access/1.5-SNAPSHOT/war
    
    


    uninstall io.hawt.hawtio-json-schema-mbean
    uninstall io.hawt.hawtio-osgi-jmx
    uninstall io.hawt.hawtio-web
    uninstall io.hawt.hawtio-fabric8-branding
    uninstall io.hawt.hawtio-karaf-terminal
    
    install -s mvn:io.hawt/hawtio-json-schema-mbean/1.5-SNAPSHOT
    install -s mvn:io.hawt/hawtio-osgi-jmx/1.5-SNAPSHOT
    install -s mvn:io.hawt/hawtio-web/1.5-SNAPSHOT/war
    install -s mvn:io.hawt/hawtio-fabric8-branding/1.5-SNAPSHOT/war
    install -s mvn:io.hawt/hawtio-karaf-terminal/1.5-SNAPSHOT/war
