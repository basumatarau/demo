package com.vironit.basumatarau;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;

import javax.net.ssl.SSLContext;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SslDemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test-ssl")
public class SslDemoApplicationTests {

	@Value("${ssldomain}")
	private String url;

	@Value("${trust.store}")
	private Resource trustStore;

	@Value("${trust.password}")
	private String trustStorePassword;

	@Test
	public void whenGetAnHttpsResource_thenValidCertificateConfigured() throws Exception {
		ResponseEntity<String> response = restTemplate().getForEntity(url, String.class, Collections.emptyMap());

		//assertEquals("<h1>Welcome to Secured Site</h1>", response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}


	RestTemplate restTemplate() throws Exception {
		SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(trustStore.getURL(), trustStorePassword.toCharArray())
				.build();
		SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(sslContext);

		HttpClient httpClient = HttpClients.custom()
				.setSSLSocketFactory(socketFactory)
				.build();

		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);

		return new RestTemplate(factory);
	}
}
