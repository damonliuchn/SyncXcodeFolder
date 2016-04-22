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

import java.util.Date;
import java.util.Map;

public interface Dict extends Map<String, Object>
{
  String getString(String key);

  void setString(String key, String value);

  Integer getInteger(String key);

  void setInteger(String key, Integer value);

  Double getDouble(String key);

  void setDouble(String key, Double value);

  Boolean getBool(String key);

  void setBool(String key, Boolean value);

  Date getDate(String key);

  void setDate(String key, Date value);

  byte[] getData(String key);

  String getDataAsUTF8String(String key);

  void setData(String key, byte[] value);

  void setDataAsUTF8String(String key, String value);

  Array getArray(String key);

  Array getOrCreateAndSetArray(String key);

  void setArray(String key, Array value);

  Dict getDict(String key);

  Dict getOrCreateAndSetDict(String key);

  void setDict(String key, Dict value);
}
