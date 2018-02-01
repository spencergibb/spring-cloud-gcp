/*
 *  Copyright 2017 original author or authors.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.springframework.cloud.gcp.autoconfigure.config;

import java.io.IOException;

import com.google.api.gax.core.CredentialsProvider;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gcp.autoconfigure.core.GcpContextAutoConfiguration;
import org.springframework.cloud.gcp.core.GcpProjectIdProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Bootstrap auto configuration for Google Cloud Runtime Configurator Starter.
 *
 * @author Jisha Abubaker
 * @author João André Martins
 */
@Configuration
@AutoConfigureAfter(GcpContextAutoConfiguration.class)
@EnableConfigurationProperties(GcpConfigProperties.class)
public class GcpConfigAutoConfiguration {

	@Bean
	@ConditionalOnProperty(prefix = "spring.cloud.gcp.config", name = "enabled", havingValue = "true")
	public GoogleConfigPropertySourceLocator googleConfigPropertySourceLocator(GcpProjectIdProvider projectIdProvider,
			CredentialsProvider credentialsProvider,
			GcpConfigProperties configProperties) throws IOException {
		return new GoogleConfigPropertySourceLocator(projectIdProvider, credentialsProvider, configProperties);
	}
}
