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
package com.sap.prd.mobile.ios.mios.xcodeprojreader.jaxb;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;

import com.sap.prd.mobile.ios.mios.xcodeprojreader.Array;
import com.sap.prd.mobile.ios.mios.xcodeprojreader.Dict;

class ArrayListArray extends ArrayList<Object> implements Array
{
  private static final long serialVersionUID = -7528319956295843074L;

  @Override
  public String getString(int index)
  {
    return (String) get(index);
  }

  @Override
  public void addString(String value)
  {
    add(value);
  }

  @Override
  public void setString(int index, String value)
  {
    set(index, value);
  }

  @Override
  public Integer getInteger(int index)
  {
    return (Integer) get(index);
  }

  @Override
  public void addInteger(Integer value)
  {
    add(value);
  }

  @Override
  public void setInteger(int index, Integer value)
  {
    set(index, value);
  }

  @Override
  public Double getDouble(int index)
  {
    return (Double) get(index);
  }

  @Override
  public void addDouble(Double value)
  {
    add(value);
  }

  @Override
  public void setDouble(int index, Double value)
  {
    set(index, value);
  }

  @Override
  public Boolean getBool(int index)
  {
    return (Boolean) get(index);
  }

  @Override
  public void addBool(Boolean value)
  {
    add(value);
  }

  @Override
  public void setBool(int index, Boolean value)
  {
    set(index, value);
  }

  @Override
  public Date getDate(int index)
  {
    return (Date) get(index);
  }

  @Override
  public void setDate(int index, Date value)
  {
    set(index, value);
  }

  @Override
  public void addDate(Date value)
  {
    add(value);
  }

  @Override
  public byte[] getData(int index)
  {
    return (byte[]) get(index);
  }

  @Override
  public String getDataAsUTF8String(int index)
  {
    byte[] bytes = (byte[]) get(index);
    try
    {
      return new String(bytes, "UTF-8");
    }
    catch (UnsupportedEncodingException e)
    {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void setData(int index, byte[] value)
  {
    set(index, value);
  }

  @Override
  public void setDataAsUTF8String(int index, String value)
  {
    try
    {
      set(index, value.getBytes("UTF-8"));
    }
    catch (UnsupportedEncodingException e)
    {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void addData(byte[] value)
  {
    add(value);
  }

  @Override
  public void addDataAsUTF8String(String value)
  {
    try
    {
      add(value.getBytes("UTF-8"));
    }
    catch (UnsupportedEncodingException e)
    {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Dict getDict(int index)
  {
    return (Dict) get(index);
  }

  @Override
  public void addDict(Dict value)
  {
    add(value);
  }

  @Override
  public void setDict(int index, Dict value)
  {
    set(index, value);
  }

  @Override
  public Array getArray(int index)
  {
    return (Array) get(index);
  }

  @Override
  public void addArray(Array value)
  {
    add(value);
  }

  @Override
  public void setArray(int index, Array value)
  {
    set(index, value);
  }

}
