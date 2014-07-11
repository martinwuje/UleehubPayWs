/*
 * Created on 2006-2-19
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icbc.ssl;

/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
import java.security.cert.X509Certificate;

import com.sun.net.ssl.X509TrustManager;
import org.apache.commons.logging.Log; 
import org.apache.commons.logging.LogFactory;

public class AuthSSLX509TrustManager implements X509TrustManager
{
    private X509TrustManager defaultTrustManager = null;

    /** Log object for this class. */
    private static final Log LOG = LogFactory.getLog(AuthSSLX509TrustManager.class);

    /**
     * Constructor for AuthSSLX509TrustManager.
     */
    public AuthSSLX509TrustManager(final X509TrustManager defaultTrustManager) {
        super();
        if (defaultTrustManager == null) {
            throw new IllegalArgumentException("Trust manager may not be null");
        }
        this.defaultTrustManager = defaultTrustManager;
    }

    /**
     * @see com.sun.net.ssl.X509TrustManager#isClientTrusted(X509Certificate[])
     */
    public boolean isClientTrusted(X509Certificate[] certificates) {
        if (LOG.isInfoEnabled() && certificates != null) {
            for (int c = 0; c < certificates.length; c++) {
                X509Certificate cert = certificates[c];
                LOG.info(" Client certificate " + (c + 1) + ":");
                LOG.info("  Subject DN: " + cert.getSubjectDN());
                LOG.info("  Signature Algorithm: " + cert.getSigAlgName());
                LOG.info("  Valid from: " + cert.getNotBefore() );
                LOG.info("  Valid until: " + cert.getNotAfter());
                LOG.info("  Issuer: " + cert.getIssuerDN());
            }
        }
        return this.defaultTrustManager.isClientTrusted(certificates);
    }

    /**
     * @see com.sun.net.ssl.X509TrustManager#isServerTrusted(X509Certificate[])
     */
    public boolean isServerTrusted(X509Certificate[] certificates) {
        if (LOG.isInfoEnabled() && certificates != null) {
            for (int c = 0; c < certificates.length; c++) {
                X509Certificate cert = certificates[c];
                LOG.info(" Server certificate " + (c + 1) + ":");
                LOG.info("  Subject DN: " + cert.getSubjectDN());
                LOG.info("  Signature Algorithm: " + cert.getSigAlgName());
                LOG.info("  Valid from: " + cert.getNotBefore() );
                LOG.info("  Valid until: " + cert.getNotAfter());
                LOG.info("  Issuer: " + cert.getIssuerDN());
            }
        }
        return this.defaultTrustManager.isServerTrusted(certificates);
    }

    /**
     * @see com.sun.net.ssl.X509TrustManager#getAcceptedIssuers()
     */
    public X509Certificate[] getAcceptedIssuers() {
        return this.defaultTrustManager.getAcceptedIssuers();
    }
}

 

