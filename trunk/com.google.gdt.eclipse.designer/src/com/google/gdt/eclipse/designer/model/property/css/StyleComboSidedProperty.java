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
package com.google.gdt.eclipse.designer.model.property.css;

import org.eclipse.wb.internal.core.model.property.Property;
import org.eclipse.wb.internal.core.model.property.editor.StringComboPropertyEditor;
import org.eclipse.wb.internal.core.model.property.editor.complex.IComplexPropertyEditor;
import org.eclipse.wb.internal.css.semantics.AbstractSidedProperty;

/**
 * Property for {@link AbstractSidedProperty} with choosing values list.
 * 
 * @author scheglov_ke
 * @coverage gwt.model.property
 */
class StyleComboSidedProperty extends StyleAbstractSidedProperty {
  ////////////////////////////////////////////////////////////////////////////
  //
  // Constructor
  //
  ////////////////////////////////////////////////////////////////////////////
  public StyleComboSidedProperty(RuleAccessor accessor,
      String styleName,
      String valueObjectPath,
      String title,
      String[] items) {
    super(new ComboSidedPropertyEditor(items),
        accessor,
        styleName,
        valueObjectPath,
        title,
        new Property[]{
            new StyleSimpleValueProperty(accessor,
                styleName,
                valueObjectPath + ".top",
                "top",
                new StringComboPropertyEditor(items)),
            new StyleSimpleValueProperty(accessor,
                styleName,
                valueObjectPath + ".right",
                "right",
                new StringComboPropertyEditor(items)),
            new StyleSimpleValueProperty(accessor,
                styleName,
                valueObjectPath + ".bottom",
                "bottom",
                new StringComboPropertyEditor(items)),
            new StyleSimpleValueProperty(accessor,
                styleName,
                valueObjectPath + ".left",
                "left",
                new StringComboPropertyEditor(items))});
  }

  ////////////////////////////////////////////////////////////////////////////
  //
  // PropertyEditor
  //
  ////////////////////////////////////////////////////////////////////////////
  private static class ComboSidedPropertyEditor extends StringComboPropertyEditor
      implements
        IComplexPropertyEditor {
    ////////////////////////////////////////////////////////////////////////////
    //
    // Constructor
    //
    ////////////////////////////////////////////////////////////////////////////
    public ComboSidedPropertyEditor(String... items) {
      super(items);
    }

    ////////////////////////////////////////////////////////////////////////////
    //
    // IComplexPropertyEditor
    //
    ////////////////////////////////////////////////////////////////////////////
    public Property[] getProperties(Property property) throws Exception {
      return ((StyleComboSidedProperty) property).m_properties;
    }
  }
}
