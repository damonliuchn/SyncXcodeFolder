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

import javax.xml.bind.annotation.adapters.XmlAdapter;

import com.sap.prd.mobile.ios.mios.xcodeprojreader.Array;

public class JAXBArrayAdapter extends XmlAdapter<JAXBArray, Array>
{
  @Override
  public JAXBArray marshal(Array array) throws Exception
  {
    JAXBArray jaxbArray = new JAXBArray();
    Array elements = new JAXBPlist().createArray();
    JAXBPlistElementConverter converter = new JAXBPlistElementConverter(new JAXBDictAdapter(), this);
    for (Object value : array)
    {
      value = converter.convertToJAXB(value);
      elements.add(value);
    }
    jaxbArray.setElements(elements);
    return jaxbArray;
  }

  @Override
  public Array unmarshal(JAXBArray jaxbArray) throws Exception
  {
    Array array = new JAXBPlist().createArray();
    JAXBPlistElementConverter converter = new JAXBPlistElementConverter(new JAXBDictAdapter(), this);
    for (Object value : jaxbArray.getElements())
    {
      value = converter.convertFromJAXB(value);
      array.add(value);
    }
    return array;
  }
}