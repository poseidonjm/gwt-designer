/*******************************************************************************
 * Copyright 2011 Google Inc. All Rights Reserved.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.google.gdt.eclipse.designer.gxt.model.layout.assistant;

import org.eclipse.wb.core.model.ObjectInfo;
import org.eclipse.wb.internal.core.utils.ui.GridDataFactory;
import org.eclipse.wb.internal.core.utils.ui.GridLayoutFactory;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import java.util.List;

/**
 * Assistant for GXT <code>TableData</code>.
 * 
 * @author sablin_aa
 * @coverage ExtGWT.model.layout.assistant
 */
public final class TableLayoutDataAssistantPage extends LayoutDataAssistantPage {
  public TableLayoutDataAssistantPage(Composite parent, List<ObjectInfo> selection) {
    super(parent, selection);
    GridLayoutFactory.create(this).columns(2);
    // align
    GridDataFactory.create(
        addEnumProperty(
            this,
            "horizontalAlign",
            "Horizontal align",
            "com.extjs.gxt.ui.client.Style$HorizontalAlignment")).fillH();
    GridDataFactory.create(
        addEnumProperty(
            this,
            "verticalAlign",
            "Vertical align",
            "com.extjs.gxt.ui.client.Style$VerticalAlignment")).fillH();
    // others
    {
      Composite composite = new Composite(this, SWT.NONE);
      GridLayoutFactory.create(composite).columns(2);
      addIntegerProperty(composite, "colspan", "colspan");
      addIntegerProperty(composite, "rowspan", "rowspan");
      addIntegerProperty(composite, "margin", "margin");
      addIntegerProperty(composite, "padding", "padding");
    }
    {
      Composite composite = new Composite(this, SWT.NONE);
      GridLayoutFactory.create(composite).columns(2);
      addStringProperty(composite, "height", "height");
      addStringProperty(composite, "width", "width");
      addStringProperty(composite, "style");
      addStringProperty(composite, "styleName");
    }
  }
}