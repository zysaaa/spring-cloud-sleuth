/*
 * Copyright 2013-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.cloud.sleuth.zipkin2;

import zipkin2.reporter.Sender;

import org.springframework.web.client.RestTemplate;

import static zipkin2.codec.SpanBytesEncoder.JSON_V2;
import static zipkin2.codec.SpanBytesEncoder.PROTO3;

class RestTemplateSenderTest extends AbstractSenderTest {

	@Override
	Sender jsonSender() {
		return new RestTemplateSender(new RestTemplate(), this.endpoint, null, JSON_V2);
	}

	@Override
	Sender jsonSender(String mockedApiPath) {
		return new RestTemplateSender(new RestTemplate(), this.endpoint, mockedApiPath, JSON_V2);
	}

	@Override
	Sender protoSender() {
		return new RestTemplateSender(new RestTemplate(), this.endpoint, "", PROTO3);
	}

	@Override
	String expectedToString() {
		return "RestTemplateSender{" + this.endpoint + "/api/v2/spans}";
	}

	@Override
	String expectedToStringWithNonEmptyApiPath(String mockedApiPath) {
		if ("".equals(mockedApiPath)) {
			return "RestTemplateSender{" + this.endpoint + "}";
		}
		return "RestTemplateSender{" + this.endpoint + mockedApiPath + "}";
	}

}
