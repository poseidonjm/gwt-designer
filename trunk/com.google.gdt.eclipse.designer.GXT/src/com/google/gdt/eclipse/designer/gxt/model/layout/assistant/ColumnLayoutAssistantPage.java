/*
 * Copyright 2011 Google Inc. All Rights Reserved.
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
package com.google.gdt.eclipse.designer.gxt.model.layout.assistant;

import com.google.gdt.eclipse.designer.gxt.model.layout.ColumnLayoutInfo;

import org.eclipse.wb.internal.core.utils.ui.GridDataFactory;
import org.eclipse.wb.internal.core.utils.ui.GridLayoutFactory;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

/**
 * Assistant for {@link ColumnLayoutInfo}.
 * 
 * @author sablin_aa
 * @coverage ExtGWT.model.layout.assistant
 */
public final class ColumnLayoutAssistantPage extends AbstractExtGwtAssistantPage {
  public ColumnLayoutAssistantPage(Composite parent, ColumnLayoutInfo layout) {
    super(parent, layout);
    GridLayoutFactory.create(this).columns(2);
    // options
    {
      Group optionsGroup =
          addBooleanProperties(this, "Options", new String[]{
              "adjustForScroll",
              "firesEvents",
              "renderHidden"});
      GridDataFactory.create(optionsGroup).fill();
    }
  }
}