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
package com.google.gdt.eclipse.designer.smart.model;

import com.google.gdt.eclipse.designer.model.widgets.WidgetTopBoundsSupport;
import com.google.gdt.eclipse.designer.smart.model.support.SmartClientUtils;

import org.eclipse.wb.draw2d.geometry.Dimension;
import org.eclipse.wb.internal.core.model.TopBoundsSupport;

/**
 * Implementation of {@link TopBoundsSupport} for any {@link CanvasInfo}.
 * 
 * @author sablin_aa
 * @coverage SmartGWT.model
 */
public class CanvasTopBoundsSupport extends WidgetTopBoundsSupport {
  private final CanvasInfo m_canvas;

  ////////////////////////////////////////////////////////////////////////////
  //
  // Constructor
  //
  ////////////////////////////////////////////////////////////////////////////
  public CanvasTopBoundsSupport(CanvasInfo canvas) {
    super(canvas);
    m_canvas = canvas;
  }

  ////////////////////////////////////////////////////////////////////////////
  //
  // TopBoundsSupport
  //
  ////////////////////////////////////////////////////////////////////////////
  @Override
  protected void afterApply() throws Exception {
    super.afterApply();
    // notify that object ready
    m_canvas.getBroadcast(CanvasAfterAttach.class).invoke();
  }

  @Override
  protected boolean isSizeAlreadySet() throws Exception {
    return super.isSizeAlreadySet()
        || hasMethodInvocations(new String[]{
            "setRect(int,int,int,int)",
            "setRect(com.smartgwt.client.core.Rectangle)"});
  }

  @Override
  protected Dimension getExpandedSize() throws Exception {
    return SmartClientUtils.getAbsoluteBounds(m_canvas.getObject()).getSize();
  }

  @Override
  public void setSize(int width, int height) throws Exception {
    super.setSize(width, height);
    {
      setSizeInt("setRect(int,int,int,int)", 2, width);
      // TODO "setRect(com.smartgwt.client.core.Rectangle)"
    }
    {
      setSizeInt("setRect(int,int,int,int)", 3, height);
      // TODO "setRect(com.smartgwt.client.core.Rectangle)"
    }
  }
}
