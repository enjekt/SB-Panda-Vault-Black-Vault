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
package org.enjket.panda.blackvault.service.impl;

import org.enjket.panda.blackvault.models.Pad;
import org.enjket.panda.blackvault.models.Token;
import org.enjket.panda.blackvault.models.TokenPadPair;
import org.enjket.panda.blackvault.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class BlackVaultDatabase.
 */
@Service("databaseService")
@Transactional
public class BlackVaultDatabase implements DatabaseService{

	/** The JPA repository used to store data */
	@Autowired
	BlackVaultRepository repository;
	
	/* (non-Javadoc)
	 * @see org.enjket.panda.blackvault.service.DatabaseService#store(org.enjket.panda.blackvault.models.TokenPadPair)
	 */
	@Override
	public void store(TokenPadPair pair) {
		repository.saveAndFlush(pair);
		
	}

	/* (non-Javadoc)
	 * @see org.enjket.panda.blackvault.service.DatabaseService#getPad(org.enjket.panda.blackvault.models.Token)
	 */
	@Override
	public Pad getPad(Token token) {
		System.out.println("Get panda for token: "+token.getToken());
		TokenPadPair pair = repository.findByToken(token.getToken());
		System.out.println(pair.toString());
		return new Pad(pair.getPad());
	}

	/* (non-Javadoc)
	 * @see org.enjket.panda.blackvault.service.DatabaseService#deleteToken(org.enjket.panda.blackvault.models.Token)
	 */
	@Override
	public void deleteToken(Token token) {
		
	}

}
