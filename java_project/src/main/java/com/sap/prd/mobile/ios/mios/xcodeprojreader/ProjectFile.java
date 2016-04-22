/*
 * #%L
 * xcode-project-reader
 * %%
 * Copyright (C) 2012 SAP AG
 * %%
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
 * #L%
 */
package com.sap.prd.mobile.ios.mios.xcodeprojreader;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.apache.commons.codec.binary.Hex;

public class ProjectFile extends Element
{
  private final Plist plist;

  public ProjectFile(Plist plist)
  {
    super(null, plist.getDict());
    this.plist = plist;
  }

  public Plist getPlist()
  {
    return plist;
  }

  public Array createArray()
  {
    return plist.createArray();
  }

  public Dict createDict()
  {
    return plist.createDict();
  }

  public String getVersion()
  {
    return plist.getVersion();
  }

  public String getObjectVersion()
  {
    return getDict().getString("objectVersion");
  }

  public Dict getObjectByReference(String reference)
  {
    return (Dict) getObjects().get(reference);
  }

  public void setObjectByReference(String reference, Dict object)
  {
    getObjects().put(reference, object);
  }

  public String generateReference()
  {
    MessageDigest md = null;
    SecureRandom prng = null;
    try
    {
      md = MessageDigest.getInstance("SHA1");
      prng = SecureRandom.getInstance("SHA1PRNG");
    }
    catch (NoSuchAlgorithmException e)
    {
    }

    String randomNum = new Integer(prng.nextInt()).toString();
    String ref = new String(Hex.encodeHex(md.digest(randomNum.getBytes())));
    return ref.toUpperCase().substring(0, 24);
  }

  private Dict getObjects()
  {
    return getDict().getDict("objects");
  }

  public Project getProject()
  {
    String projectRef = getDict().getString("rootObject");
    Dict project = getObjectByReference(projectRef);
    return new Project(this, project);
  }
}
