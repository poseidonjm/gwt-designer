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
package com.google.gdt.eclipse.designer.uibinder.gef.part;

import com.google.gdt.eclipse.designer.gef.policy.DockLayoutPanelLayoutEditPolicy;
import com.google.gdt.eclipse.designer.uibinder.model.widgets.DockLayoutPanelInfo;

import org.eclipse.wb.gef.core.EditPart;
import org.eclipse.wb.gef.core.policies.EditPolicy;

/**
 * {@link EditPart} for {@link DockLayoutPanelInfo}.
 * 
 * @author scheglov_ke
 * @coverage GWT.UiBinder.gef
 */
public final class DockLayoutPanelEditPart extends PanelEditPart {
  private final DockLayoutPanelInfo m_panel;

  ////////////////////////////////////////////////////////////////////////////
  //
  // Constructor
  //
  ////////////////////////////////////////////////////////////////////////////
  public DockLayoutPanelEditPart(DockLayoutPanelInfo panel) {
    super(panel);
    m_panel = panel;
  }

  ////////////////////////////////////////////////////////////////////////////
  //
  // Policy
  //
  ////////////////////////////////////////////////////////////////////////////
  @Override
  protected void createEditPolicies() {
    super.createEditPolicies();
    installEditPolicy(EditPolicy.LAYOUT_ROLE, DockLayoutPanelLayoutEditPolicy.create(m_panel));
  }
}
