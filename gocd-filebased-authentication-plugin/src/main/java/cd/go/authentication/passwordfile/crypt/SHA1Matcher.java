/*
 * Copyright 2017 ThoughtWorks, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cd.go.authentication.passwordfile.crypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import static org.apache.commons.lang3.StringUtils.equalsAny;

public class SHA1Matcher implements HashMatcher {

    @Override
    public boolean matches(String plainText, String hashed) {
        final String digest = sha1Digest(plainText.getBytes());

        return equalsAny(hashed, digest, "{SHA}" + digest);
    }

    private String sha1Digest(byte[] bytes) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA1");
            return Base64.getEncoder().encodeToString(md.digest(bytes));
        } catch (NoSuchAlgorithmException e) {
            throw new cd.go.authentication.passwordfile.exception.NoSuchAlgorithmException(e);
        }
    }
}