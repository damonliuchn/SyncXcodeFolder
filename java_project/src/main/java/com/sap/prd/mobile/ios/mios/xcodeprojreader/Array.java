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
import java.util.List;

public interface Array extends List<Object>
{
  String getString(int index);

  void addString(String value);

  void setString(int index, String value);

  Integer getInteger(int index);

  void addInteger(Integer value);

  void setInteger(int index, Integer value);

  Double getDouble(int index);

  void addDouble(Double value);

  void setDouble(int index, Double value);

  Boolean getBool(int index);

  void addBool(Boolean value);

  void setBool(int index, Boolean value);

  Date getDate(int index);

  void setDate(int index, Date value);

  void addDate(Date value);

  byte[] getData(int index);

  String getDataAsUTF8String(int index);

  void setData(int index, byte[] value);

  void setDataAsUTF8String(int index, String value);

  void addData(byte[] value);

  void addDataAsUTF8String(String value);

  Dict getDict(int index);

  void addDict(Dict value);

  void setDict(int index, Dict value);

  Array getArray(int index);

  void addArray(Array value);

  void setArray(int index, Array value);

}
