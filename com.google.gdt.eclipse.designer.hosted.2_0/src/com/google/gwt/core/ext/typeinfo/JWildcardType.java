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
package com.google.gwt.core.ext.typeinfo;

/**
 * Represents a wildcard type argument to a parameterized type.
 */
public class JWildcardType extends JDelegatingClassType {
  /**
   * Type of wildcard bound.
   */
  public enum BoundType {
    /**
     * Used when the declaration explicitly used ? extends Type.
     */
    EXTENDS,

    /**
     * Used when the declaration explicitly used ? super Type.
     */
    SUPER,

    /**
     * Used when the declaration did not specify a bound.
     */
    UNBOUND
  }

  private final BoundType boundType;
  private JClassType[] lazyLowerBounds;
  private JClassType[] lazyUpperBounds;

  JWildcardType(BoundType boundType, JClassType typeBound) {
    this.boundType = boundType;
    super.setBaseType(typeBound);
  }

  @Override
  public JField findField(String name) {
    return getBaseType().findField(name);
  }

  @Override
  public JMethod findMethod(String name, JType[] paramTypes) {
    return getBaseType().findMethod(name, paramTypes);
  }

  public BoundType getBoundType() {
    return boundType;
  }

  @Override
  public JClassType getErasedType() {
    return getUpperBound().getErasedType();
  }

  @Override
  public JField getField(String name) {
    return getBaseType().getField(name);
  }

  @Override
  public JField[] getFields() {
    return getBaseType().getFields();
  }

  public JClassType getFirstBound() {
    return getBaseType();
  }

  /**
   * Returns the lower bounds of this wildcard type. If no lower bounds were
   * declared, an empty array is returned.
   * 
   * @return the lower bounds of this wildcard type
   */
  public JClassType[] getLowerBounds() {
    if (lazyLowerBounds == null) {
      if (isUpperBound()) {
        lazyLowerBounds = TypeOracle.NO_JCLASSES;
      } else {
        lazyLowerBounds = new JClassType[] {getFirstBound()};
      }
    }
    return lazyLowerBounds;
  }

  @Override
  public JMethod getMethod(String name, JType[] paramTypes)
      throws NotFoundException {
    return getBaseType().getMethod(name, paramTypes);
  }

  @Override
  public JMethod[] getMethods() {
    return getBaseType().getMethods();
  }

  @Override
  public String getQualifiedBinaryName() {
    // TODO(jat): !! does a binary name have meaning for a wildcard?
    return toString(true);
  }

  @Override
  public String getQualifiedSourceName() {
    return toString(false);
  }

  @Override
  public String getSimpleSourceName() {
    return toString(true);
  }

  @Override
  public JClassType[] getSubtypes() {
    if (isUpperBound()) {
      return getFirstBound().getSubtypes();
    }

    // We are not sure what the correct behavior should be for lower bound
    // wildcards. ? super Number contains ? super T for all T extends Number,
    // but it also includes T for Number extends T. For example, Object is a
    // subtype.
    return TypeOracle.NO_JCLASSES;
  }

  @Override
  public JClassType getSuperclass() {
    if (isUpperBound()) {
      // The superclass of an upper bound is the upper bound.
      return getFirstBound();
    }

    // The only safe superclass for a ? super T is Object.
    return getOracle().getJavaLangObject();
  }

  public JClassType getUpperBound() {
    if (isUpperBound()) {
      return getFirstBound();
    }

    return getOracle().getJavaLangObject();
  }

  /**
   * Returns the upper bounds of this wildcard type. If no upper bounds were
   * declared, an array containing {@link Object} is returned.
   * 
   * @return the upper bounds of this wildcard type
   */
  public JClassType[] getUpperBounds() {
    if (lazyUpperBounds == null) {
      if (isUpperBound()) {
        lazyUpperBounds = new JClassType[] {getFirstBound()};
      } else {
        // Object is the default upper bound.
        lazyUpperBounds = new JClassType[] {getOracle().getJavaLangObject()};
      }
    }

    return lazyUpperBounds;
  }

  @Override
  public JGenericType isGenericType() {
    return null;
  }

  @Override
  public JParameterizedType isParameterized() {
    return null;
  }

  @Override
  public JRawType isRawType() {
    return null;
  }

  @Override
  public JWildcardType isWildcard() {
    return this;
  }

  /**
   * Returns <code>true</code> if this instance has the same bounds that are
   * requested.
   * 
   * @param otherWildcard
   * @return <code>true</code> if this instance has the same bounds that are
   *         requested
   */
  boolean boundsMatch(JWildcardType otherWildcard) {
    return isUpperBound() == otherWildcard.isUpperBound()
        && getFirstBound() == otherWildcard.getFirstBound();
  }

  @Override
  JClassType getSubstitutedType(JParameterizedType parameterizedType) {
    return getOracle().getWildcardType(boundType,
        getFirstBound().getSubstitutedType(parameterizedType));
  }

  private boolean isUnbound() {
    return boundType == BoundType.UNBOUND;
  }

  private boolean isUpperBound() {
    return boundType != BoundType.SUPER;
  }

  private String toString(boolean simpleName) {
    String str = "?";
    if (isUnbound()) {
      return str;
    } else {
      str += (isUpperBound() ? " extends " : " super ");
      if (simpleName) {
        return str + getFirstBound().getSimpleSourceName();
      } else {
        return str + getFirstBound().getParameterizedQualifiedSourceName();
      }
    }
  }
}
