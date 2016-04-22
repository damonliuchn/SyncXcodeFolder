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

import java.util.Iterator;

public class ReferenceArray<T extends Element> implements Iterable<T>
{
  private final ProjectFile projectFile;
  private final Array refs;
  private final ElementFactory<T> elementFactory;

  public ReferenceArray(ProjectFile projectFile, Array refs, ElementFactory<T> elementFactory)
  {
    this.projectFile = projectFile;
    this.refs = refs;
    this.elementFactory = elementFactory;
  }

  @Override
  public Iterator<T> iterator()
  {
    return new Iterator<T>() {

      private Iterator<Object> refsIterator = refs.iterator();

      @Override
      public boolean hasNext()
      {
        return refsIterator.hasNext();
      }

      @Override
      public T next()
      {
        return createObjectFromRef((String) refsIterator.next());
      }

      @Override
      public void remove()
      {
        throw new UnsupportedOperationException();
      }
    };
  }

  private T createObjectFromRef(String ref)
  {
    return elementFactory.create(projectFile, projectFile.getObjectByReference(ref));
  }

  public T get(int index)
  {
    String ref = (String) refs.get(index);
    return createObjectFromRef(ref);
  }

  public T getByName(String name)
  {
    for (T object : this)
    {
      if (name.equals(object.getDict().getString("name")))
      {
        return object;
      }
    }
    return null;
  }

  public int size()
  {
    return refs.size();
  }

  public void add(T object)
  {
    String ref = projectFile.generateReference();
    projectFile.setObjectByReference(ref, object.getDict());
    refs.add(ref);
  }
}
