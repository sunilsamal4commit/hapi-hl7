/**
The contents of this file are subject to the Mozilla Public License Version 1.1 
(the "License"); you may not use this file except in compliance with the License. 
You may obtain a copy of the License at http://www.mozilla.org/MPL/ 
Software distributed under the License is distributed on an "AS IS" basis, 
WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for the 
specific language governing rights and limitations under the License. 

The Original Code is "DefaultValidationWithoutTN.java".  Description: 
"A ValidationContext with a default set of rules initially defined except for
the TN data type." 

The Initial Developer of the Original Code is University Health Network. Copyright (C) 
2012.  All Rights Reserved. 

Contributor(s): ______________________________________. 

Alternatively, the contents of this file may be used under the terms of the 
GNU General Public License (the  �GPL�), in which case the provisions of the GPL are 
applicable instead of those above.  If you wish to allow use of your version of this 
file only under the terms of the GPL and not to allow others to use your version 
of this file under the MPL, indicate your decision by deleting  the provisions above 
and replace  them with the notice and other provisions required by the GPL License.  
If you do not delete the provisions above, a recipient may use your version of 
this file under either the MPL or the GPL. 
 */
package ca.uhn.hl7v2.validation.impl;

import ca.uhn.hl7v2.validation.impl.builder.support.DefaultValidationWithoutTNBuilder;
import ca.uhn.hl7v2.validation.impl.builder.support.NoValidationBuilder;

/**
 * A <code>ValidationContext</code> with a default set of rules initially
 * defined. This can be used as-is for a reasonable level of primitive type
 * validation.
 * <p>
 * Subclassing this class is discouraged. You should instead subclass
 * {@link NoValidationBuilder} and use this to instantiate an instance of
 * {@link ValidationContextImpl}.
 * 
 * @author Bryan Tripp
 * @author Christian Ohr
 */
@SuppressWarnings("serial")
public class DefaultValidationWithoutTN extends ValidationContextImpl {

	public DefaultValidationWithoutTN() {
		super(new DefaultValidationWithoutTNBuilder());
	}
}
