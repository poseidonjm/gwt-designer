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
package com.google.gdt.eclipse.designer.mobile;

import org.eclipse.wb.internal.core.BundleResourceProvider;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

import java.io.InputStream;

/**
 * The activator class controls the plug-in life cycle.
 * 
 * @author scheglov_ke
 * @coverage gwt.mobile
 */
public class Activator extends AbstractUIPlugin {
  public static final String PLUGIN_ID = "com.google.gdt.eclipse.designer.mobile";
  private static Activator m_plugin;

  ////////////////////////////////////////////////////////////////////////////
  //
  // Life cycle
  //
  ////////////////////////////////////////////////////////////////////////////
  @Override
  public void stop(BundleContext context) throws Exception {
    m_plugin = null;
    super.stop(context);
  }

  @Override
  public void start(BundleContext context) throws Exception {
    super.start(context);
    m_plugin = this;
  }

  ////////////////////////////////////////////////////////////////////////////
  //
  // Instance access
  //
  ////////////////////////////////////////////////////////////////////////////
  /**
   * @return the shared instance.
   */
  public static Activator getDefault() {
    return m_plugin;
  }

  /**
   * @return this {@link Bundle}, can be used even without starting this plugin.
   */
  private static Bundle getBundleStatic() {
    return Platform.getBundle(PLUGIN_ID);
  }

  /**
   * @return the {@link String} from bundle localization.
   */
  public static String getResourceString(String key) {
    return Platform.getResourceString(getBundleStatic(), key);
  }

  ////////////////////////////////////////////////////////////////////////////
  //
  // Resources
  //
  ////////////////////////////////////////////////////////////////////////////
  private static final BundleResourceProvider m_resourceProvider =
      BundleResourceProvider.get(PLUGIN_ID);

  /**
   * @return the {@link InputStream} for file from plugin directory.
   */
  public static InputStream getFile(String path) {
    return m_resourceProvider.getFile(path);
  }

  /**
   * @return the {@link Image} from "icons" directory, with caching.
   */
  public static Image getImage(String path) {
    return m_resourceProvider.getImage("icons/" + path);
  }

  /**
   * @return the {@link ImageDescriptor} from "icons" directory.
   */
  public static ImageDescriptor getImageDescriptor(String path) {
    return m_resourceProvider.getImageDescriptor("icons/" + path);
  }
}
