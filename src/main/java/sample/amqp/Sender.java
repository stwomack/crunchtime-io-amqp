/*
 * Copyright 2012-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sample.amqp;

import javax.annotation.PostConstruct;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Autowired;

public class Sender {

//	@Autowired
//	private RabbitTemplate rabbitTemplate;

	@Autowired
	private AmqpAdmin amqpAdmin;

	@PostConstruct
	public void setUpQueue() {
	    Queue queue = new Queue("myQueue");
	    this.amqpAdmin.declareQueue(queue);
		TopicExchange exchange = new TopicExchange("chatExchange");
		this.amqpAdmin.declareBinding(BindingBuilder.bind(queue).to(exchange).with("*"));
	}

//	@Scheduled(fixedDelay = 1000L)
//	public void send() {
//		this.rabbitTemplate.convertAndSend("messages", "hello");
//	}

}
