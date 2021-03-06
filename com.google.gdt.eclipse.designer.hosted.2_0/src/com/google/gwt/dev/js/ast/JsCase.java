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

import com.google.gwt.dev.jjs.SourceInfo;

/**
 * Represents the JavaScript case statement.
 */
public final class JsCase extends JsSwitchMember {

  private JsExpression caseExpr;

  public JsCase(SourceInfo sourceInfo) {
    super(sourceInfo);
  }

  public JsExpression getCaseExpr() {
    return caseExpr;
  }

  public void setCaseExpr(JsExpression caseExpr) {
    this.caseExpr = caseExpr;
  }

  public void traverse(JsVisitor v, JsContext<JsSwitchMember> ctx) {
    if (v.visit(this, ctx)) {
      caseExpr = v.accept(caseExpr);
      v.acceptWithInsertRemove(stmts);
    }
    v.endVisit(this, ctx);
  }
}
