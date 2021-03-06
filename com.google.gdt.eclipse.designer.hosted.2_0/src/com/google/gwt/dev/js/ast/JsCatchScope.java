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
package com.google.gwt.dev.js.ast;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A special scope used only for catch blocks. It only holds a single symbol:
 * the catch argument's name.
 */
public class JsCatchScope extends JsScope {

  private final JsName name;

  public JsCatchScope(JsScope parent, String ident) {
    super(parent, "Catch scope");
    this.name = new JsName(this, ident, ident);
  }

  @Override
  public JsName declareName(String ident) {
    // Declare into parent scope!
    return getParent().declareName(ident);
  }

  @Override
  public JsName declareName(String ident, String shortIdent) {
    // Declare into parent scope!
    return getParent().declareName(ident, shortIdent);
  }

  @Override
  public Iterator<JsName> getAllNames() {
    return new Iterator<JsName>() {
      private boolean didIterate = false;

      public boolean hasNext() {
        return !didIterate;
      }

      public JsName next() {
        if (didIterate) {
          throw new NoSuchElementException();
        }
        didIterate = true;
        return name;
      }

      public void remove() {
        throw new UnsupportedOperationException();
      }

    };
  }

  @Override
  protected JsName doCreateName(String ident, String shortIdent) {
    throw new UnsupportedOperationException(
        "Cannot create a name in a catch scope");
  }

  @Override
  protected JsName findExistingNameNoRecurse(String ident) {
    if (name.getIdent().equals(ident)) {
      return name;
    } else {
      return null;
    }
  }

}
