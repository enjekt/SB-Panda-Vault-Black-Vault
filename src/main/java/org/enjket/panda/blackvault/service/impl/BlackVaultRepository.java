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

import org.enjket.panda.blackvault.models.TokenPadPair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The Interface BlackVaultRepository used to fetch the pad for a given token or a list of
 * tokens and pads for a given "family".  The family consists of the last 4 digits of a card
 * and the first 6 digits of the card.  The family is used to pull pads from the database
 * so that we can sure idempotent insertion of PANs into the system.
 */
@Repository
public interface BlackVaultRepository extends JpaRepository<TokenPadPair, Long> {

	/**
	 * Find the pad associated with a given token.
	 *
	 * @param token the token
	 * @return the token pad pair
	 */
	TokenPadPair findByToken(String token);

	void deleteByToken(String token);
}