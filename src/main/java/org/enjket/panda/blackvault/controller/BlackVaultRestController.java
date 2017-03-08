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
package org.enjket.panda.blackvault.controller;

import org.enjket.panda.blackvault.models.Pad;
import org.enjket.panda.blackvault.models.Token;
import org.enjket.panda.blackvault.models.TokenPadPair;
import org.enjket.panda.blackvault.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.Size;


/**
 * The Class BlackVaultRestController front end for adding token and pad pairs to the datastore, for
 * returning the pad for a given token, for deleting the token and associated data, and for returning
 * a list of pads associated with a family of tokens.
 */
@RestController
public class BlackVaultRestController {
	
	private static final Logger logger = LoggerFactory.getLogger(BlackVaultRestController.class);

	/** The datastore service. */
	@Autowired
	DatabaseService datastore;
	
	/**
	 * Gets the pad associated with a token. This is called by the White Vault to retrieve the pad so that
	 * it can reconstitute the PAN.
	 *
	 * @param tokenL the id is the numeric representation of the token. While the token internally is used
	 * as a String, using the long as a PathVariable ensures that only numeric values are accepted.
	 * @return the Pad - the random number associated with the token. The pad is required to restore the
	 * PAN in the White Vault.
	 */
	@Validated
	@RequestMapping(value = "/pad/{token}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Pad> getPad(@PathVariable("token") @Size(min = 16, max = 116, message = "Token must be of length 16")  long tokenL) {
		logger.debug("Fetching token with id " + tokenL);
		Token token = new Token(String.valueOf(tokenL));
		logger.debug("The token value: " + token.getToken());
		Pad pad = datastore.getPad(token);
		logger.debug("The pad value: " + pad.getPad());

		//TODO Error handling and reporting
		/*if (panda == null) {
			return new ResponseEntity<Pan>(HttpStatus.NOT_FOUND);
		}*/

		return new ResponseEntity<Pad>(pad, HttpStatus.OK);
	}

	
	
	
	/**
	 * Store the pad keyed by the token.
	 *
	 * @param pair the pair
	 * @return the response entity
	 */
	@RequestMapping(value = "/pad", method = RequestMethod.POST)
	public ResponseEntity<?> storePad( @RequestBody TokenPadPair pair) {
	
		datastore.store(pair);
	
		return new ResponseEntity<>("", HttpStatus.CREATED);
	
	}

	
	

	//------------------- Delete a User --------------------------------------------------------
	
	/**
	 * Delete token and associate pad
	 *
	 * @param tokenL the token to use as a key.
	 */
	@RequestMapping(value = "/token/{token}", method = RequestMethod.DELETE)
    @Validated
	public void deleteToken(@PathVariable("token") @Size(min = 16, max = 116, message = "Token must be of length 16")  long tokenL) {
		logger.debug("Deleting Token with id " + tokenL);
		
		datastore.deleteToken(new Token(tokenL));

	}



}
