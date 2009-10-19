/**
 *
 * The contents of this file are subject to the Mozilla Public License Version 1.1
 * (the "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.mozilla.org/MPL/
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for the
 * specific language governing rights and limitations under the License.
 *
 * The Initial Developer of the Original Code is University Health Network. Copyright (C)
 * 2001.  All Rights Reserved.
 *
 * Alternatively, the contents of this file may be used under the terms of the
 * GNU General Public License (the  "GPL"), in which case the provisions of the GPL are
 * applicable instead of those above.  If you wish to allow use of your version of this
 * file only under the terms of the GPL and not to allow others to use your version
 * of this file under the MPL, indicate your decision by deleting  the provisions above
 * and replace  them with the notice and other provisions required by the GPL License.
 * If you do not delete the provisions above, a recipient may use your version of
 * this file under either the MPL or the GPL.
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.uhn.hunit.test;

import ca.uhn.hunit.event.AbstractEvent;
import ca.uhn.hunit.event.expect.ExpectNoMessageImpl;
import ca.uhn.hunit.event.expect.Hl7V2ExpectRulesImpl;
import ca.uhn.hunit.event.expect.Hl7V2ExpectSpecificMessageImpl;
import ca.uhn.hunit.event.expect.XmlExpectSpecificMessageImpl;
import ca.uhn.hunit.event.send.Hl7V2SendMessageImpl;
import ca.uhn.hunit.event.send.XmlSendMessageImpl;
import ca.uhn.hunit.ex.ConfigurationException;
import ca.uhn.hunit.iface.AbstractInterface;
import ca.uhn.hunit.xsd.ExpectMessageAny;
import ca.uhn.hunit.xsd.ExpectNoMessage;
import ca.uhn.hunit.xsd.SendMessageAny;
import ca.uhn.hunit.xsd.Test;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author James
 */
public class TestEventsModel extends AbstractTableModel implements PropertyChangeListener {

    private static final long serialVersionUID = 1L;

    private final List<AbstractEvent> myEvents = new ArrayList<AbstractEvent>();
    private final List<String> myInterfaces = new ArrayList<String>();
    private final Map<String, AbstractEvent[]> myInterfaceId2Events = new HashMap<String, AbstractEvent[]>();

    private final TestImpl myTest;

    public TestEventsModel(TestImpl theTest) {
        myTest = theTest;
    }

    public int getRowCount() {
        return myEvents.size();
    }

    public int getColumnCount() {
        return myInterfaces.size();
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        String column = myInterfaces.get(columnIndex);
        return myInterfaceId2Events.get(column)[rowIndex];
    }

    @Override
    public String getColumnName(int column) {
        return myInterfaces.get(column);
    }



    public void initFromXml(Test theConfig) throws ConfigurationException {

        myEvents.clear();

        for (Object next : theConfig.getSendMessageOrExpectMessageOrExpectNoMessage()) {
			AbstractEvent event = null;

			if (next instanceof SendMessageAny) {
				SendMessageAny nextSm = (SendMessageAny)next;
				if (nextSm.getXml() != null) {
					event = (new XmlSendMessageImpl(myTest, nextSm.getXml()));
				} else if (nextSm.getHl7V2() != null) {
					event = (new Hl7V2SendMessageImpl(myTest, nextSm.getHl7V2()));
				} else {
					throw new ConfigurationException("Unknown event type: " + next.getClass());
				}
			} else if (next instanceof ExpectMessageAny) {
				ExpectMessageAny nextEm = (ExpectMessageAny) next;
				if (nextEm.getHl7V2Specific() != null) {
					event = (new Hl7V2ExpectSpecificMessageImpl(myTest, nextEm.getHl7V2Specific()));
				} else if (nextEm.getHl7V2Rules() != null) {
					event = (new Hl7V2ExpectRulesImpl(myTest, nextEm.getHl7V2Rules()));
				} else if (nextEm.getHl7V2Ack() != null) {
					event = (new Hl7V2ExpectRulesImpl(myTest, nextEm.getHl7V2Ack()));
				} else if (nextEm.getXmlSpecific() != null) {
					event = new XmlExpectSpecificMessageImpl(myTest, nextEm.getXmlSpecific());
				} else {
					throw new ConfigurationException("Unknown event type: " + next.getClass());
				}
            } else if (next instanceof ExpectNoMessage) {
                event = new ExpectNoMessageImpl(myTest, (ExpectNoMessage)next);
			} else {
				throw new ConfigurationException("Unknown event type: " + next.getClass());
			}

			if (event == null) {
				continue;
			}

            event.addPropertyChangeListener(AbstractEvent.INTERFACE_ID_PROPERTY, this);
            myEvents.add(event);
		}

        sortInterfaces();

    }

    public List<AbstractEvent> getEventsByInterfaceId(String theInterfaceId) {
        return Arrays.asList(myInterfaceId2Events.get(theInterfaceId));
    }

    
    public List<String> getInterfaceIds() {
        return myInterfaces;
    }

    public void propertyChange(PropertyChangeEvent evt) {
        if (AbstractEvent.INTERFACE_ID_PROPERTY.equals(evt.getPropertyName())) {
            sortInterfaces();
        }
    }

    private void sortInterfaces() {
        // Sort events by interface
        myInterfaces.clear();
        myInterfaceId2Events.clear();
        int index = 0;
        for (AbstractEvent nextEvent : myEvents) {
            String interfaceId = nextEvent.getInterfaceId();
            if (!myInterfaces.contains(interfaceId)) {
                myInterfaces.add(interfaceId);
            }
            if (!myInterfaceId2Events.containsKey(interfaceId)) {
                myInterfaceId2Events.put(interfaceId, new AbstractEvent[myEvents.size()]);
            }
            AbstractEvent[] array = myInterfaceId2Events.get(interfaceId);
            array[index] = nextEvent;
            index++;
        }

        super.fireTableStructureChanged();
        super.fireTableDataChanged();
    }

    public AbstractEvent getEvent(int selectedRow) {
        return myEvents.get(selectedRow);
    }

}