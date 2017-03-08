/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.enjket.panda.blackvault.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * The Class Token has the synthetic identifier to (a) return to the corporate database
 * to store along with the expiration date, (b) act as a key to store and retrieve the
 * padded pan in the White Vault, and (c) act as a key to store and retrieve  the pad
 * from the Black Vault
 */
@Entity
public class Token implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "token")
	private String token;

	/**
	 * Instantiates a new token.
	 */
	public Token() {
 	}

	/**
	 * Instantiates a new token.
	 *
	 * @param token the token
	 */
	public Token(String token) {
		this.token=token;
	}
	public Token(long tokenL) {
		this.token=String.valueOf(tokenL);
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	/**
	 * To long.
	 *
	 * @return the long
	 */
	public long toLong(){
		return Long.parseLong(token);
	}


}
