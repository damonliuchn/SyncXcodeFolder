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

import com.sap.prd.mobile.ios.mios.xcodeprojreader.buildphases.BuildPhase;

public class Target extends NamedElement
{
  public Target(ProjectFile projectFile)
  {
    this(projectFile, projectFile.createDict());
  }

  Target(ProjectFile projectFile, Dict target)
  {
    super(projectFile, target);
  }

  public BuildConfigurationList getBuildConfigurationList()
  {
    String buildConfigurationListRef = getDict().getString("buildConfigurationList");
    Dict buildConfigurationList = getProjectFile().getObjectByReference(buildConfigurationListRef);
    return new BuildConfigurationList(getProjectFile(), buildConfigurationList);
  }

  public ReferenceArray<BuildPhase> getBuildPhases()
  {
    return new ReferenceArray<BuildPhase>(getProjectFile(), getDict().getOrCreateAndSetArray("buildPhases"),
          new ElementFactory<BuildPhase>() {
            @Override
            public BuildPhase create(ProjectFile projectFile, Dict dict)
            {
              return BuildPhase.create(projectFile, dict);
            }
          });
  }
}
