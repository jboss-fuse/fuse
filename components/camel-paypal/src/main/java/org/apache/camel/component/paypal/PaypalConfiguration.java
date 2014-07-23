/**
 * Copyright 2014 Red Hat, Inc.
 *
 * Red Hat licenses this file to you under the Apache License, version
 * 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * permissions and limitations under the License.
 *
 */
package org.apache.camel.component.paypal;

import org.apache.camel.spi.UriParam;
import org.apache.camel.spi.UriParams;
import org.apache.camel.util.ObjectHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;


/**
 * Component configuration for Paypal component.
 */
@UriParams
public class PaypalConfiguration {

    public static final String CLIENT_ID = "clientID";
    public static final String CLIENT_SECRET = "clientSecret";
    public static final String SERVICE_END_POINT = "service.EndPoint";
    public static final String HTTP_RETRY = "http.Retry";
    private static final Logger LOG = LoggerFactory.getLogger(PaypalConfiguration.class);
    public static final String HTTP_CONNECTION_TIME_OUT = "http.ConnectionTimeOut";
    public static final String HTTP_READ_TIME_OUT = "http.ReadTimeOut";
    public static final String HTTP_MAX_CONNECTION = "http.MaxConnection";
    public static final String HTTP_USE_PROXY = "http.UseProxy";
    public static final String HTTP_PROXY_PORT = "http.ProxyPort";
    public static final String HTTP_PROXY_USER_NAME = "http.ProxyUserName";
    public static final String HTTP_PROXY_HOST = "http.ProxyHost";
    public static final String HTTP_PROXY_PASSWORD = "http.ProxyPassword";
    public static final String HTTP_GOOGLE_APP_ENGINE = "http.GoogleAppEngine";
    @UriParam
    private String clientId;
    @UriParam
    private String clientSecret;
    @UriParam
    private String serviceEndpoint;
    @UriParam
    private int httpRetry;
    @UriParam
    private int httpConnectionTimeOut;
    @UriParam
    private int httpReadTimeOut;
    @UriParam
    private int httpMaxConnection;
    @UriParam
    private boolean httpUseProxy;
    @UriParam
    private String httpProxyHost;
    @UriParam
    private int httpProxyPort;
    @UriParam
    private String httpUserName;
    @UriParam
    private String httpProxyPassword;
    @UriParam
    private boolean httpGoogleAppEngine;

    public String getClientId() {
        if (clientId != null) {
            return clientId;
        } else {
            throw new IllegalStateException(String.format("%s not set", CLIENT_ID));
        }
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        if (clientSecret != null) {
            return clientSecret;
        } else {
            throw new IllegalStateException(String.format("%s not set", CLIENT_SECRET));
        }
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getServiceEndpoint() {
        if (serviceEndpoint != null) {
            return serviceEndpoint;
        } else {
            throw new IllegalStateException(String.format("%s not set", SERVICE_END_POINT));
        }
    }

    public void setServiceEndpoint(String serviceEndpoint) {
        this.serviceEndpoint = serviceEndpoint;
    }

    public int getHttpRetry() {
        return httpRetry;
    }

    public void setHttpRetry(int httpRetry) {
        this.httpRetry = httpRetry;
    }

    public int getHttpConnectionTimeOut() {
        return httpConnectionTimeOut;
    }

    public void setHttpConnectionTimeOut(int httpConnectionTimeOut) {
        this.httpConnectionTimeOut = httpConnectionTimeOut;
    }

    public int getHttpReadTimeOut() {
        return httpReadTimeOut;
    }

    public void setHttpReadTimeOut(int httpReadTimeOut) {
        this.httpReadTimeOut = httpReadTimeOut;
    }

    public int getHttpMaxConnection() {
        return httpMaxConnection;
    }

    public void setHttpMaxConnection(int httpMaxConnection) {
        this.httpMaxConnection = httpMaxConnection;
    }

    public boolean isHttpUseProxy() {
        return httpUseProxy;
    }

    public void setHttpUseProxy(boolean httpUseProxy) {
        this.httpUseProxy = httpUseProxy;
    }

    public String getHttpProxyHost() {
        return httpProxyHost;
    }

    public void setHttpProxyHost(String httpProxyHost) {
        this.httpProxyHost = httpProxyHost;
    }

    public int getHttpProxyPort() {
        return httpProxyPort;
    }

    public void setHttpProxyPort(int httpProxyPort) {
        this.httpProxyPort = httpProxyPort;
    }

    public String getHttpUserName() {
        return httpUserName;
    }

    public void setHttpUserName(String httpUserName) {
        this.httpUserName = httpUserName;
    }

    public String getHttpProxyPassword() {
        return httpProxyPassword;
    }

    public void setHttpProxyPassword(String httpProxyPassword) {
        this.httpProxyPassword = httpProxyPassword;
    }

    public boolean isHttpGoogleAppEngine() {
        return httpGoogleAppEngine;
    }

    public void setHttpGoogleAppEngine(boolean httpGoogleAppEngine) {
        this.httpGoogleAppEngine = httpGoogleAppEngine;
    }



    public void validate() {
        if (ObjectHelper.isEmpty(clientId)
                || ObjectHelper.isEmpty(clientSecret) || ObjectHelper.isEmpty(serviceEndpoint)) {
            throw new IllegalArgumentException(String.format("Missing required properties %s, %s, %s", CLIENT_ID, CLIENT_SECRET, SERVICE_END_POINT));
        }
    }

}
