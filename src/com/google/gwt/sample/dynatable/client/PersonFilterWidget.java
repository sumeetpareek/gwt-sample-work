/**
 * 
 */
package com.google.gwt.sample.dynatable.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author sumeet
 *
 */
public class PersonFilterWidget extends Composite {
	private class PersonCheckBox extends CheckBox {
		public final int personIndex;
		
		public PersonCheckBox(String caption, int personIndex) {
			super(caption);
			
			this.personIndex = personIndex;
			
			addClickHandler(personCheckBoxHandler);
			
			setValue(calendar.getPersonIncluded(personIndex));
		}
	}
	
	private class PersonCheckBoxHandler implements ClickHandler {
		public void onClick(ClickEvent event){
			onClick((PersonCheckBox) event.getSource());
		}
		
		public void onClick (PersonCheckBox personCheckBox) {
			//TODO
			calendar.setPersonIncluded(personCheckBox.personIndex, personCheckBox.getValue());
		}
	}
		
	private final SchoolCalendarWidget calendar;
	
	private final VerticalPanel outer = new VerticalPanel();
	
	private final PersonCheckBoxHandler personCheckBoxHandler = new PersonCheckBoxHandler();
	
	public PersonFilterWidget(SchoolCalendarWidget calendar) {
		this.calendar = calendar;
		initWidget(outer);
		setStyleName("DynaTable-PersonFilterWidget");
		outer.add(new PersonCheckBox("Professor", 0));
		outer.add(new PersonCheckBox("Student", 1));
		
		Button buttonAll = new Button ("All", new ClickHandler() {
			public void onClick(ClickEvent event) {
				setAllCheckBoxes(true);
			}
		});
		
		Button buttonNone = new Button ("None", new ClickHandler() {
			public void onClick(ClickEvent event) {
				setAllCheckBoxes(false);
			}
		});
		

	    HorizontalPanel hp = new HorizontalPanel();
	    hp.setHorizontalAlignment(HasAlignment.ALIGN_CENTER);
	    hp.add(buttonAll);
	    hp.add(buttonNone);

	    outer.add(hp);
	    outer.setCellVerticalAlignment(hp, HasAlignment.ALIGN_BOTTOM);
	    outer.setCellHorizontalAlignment(hp, HasAlignment.ALIGN_CENTER);
	}
	
	private void setAllCheckBoxes(boolean checked) {
	    for (int i = 0, n = outer.getWidgetCount(); i < n; ++i) {
	        Widget w = outer.getWidget(i);
	        if (w instanceof PersonCheckBox) {
	          ((PersonCheckBox) w).setValue(checked);
	          personCheckBoxHandler.onClick((PersonCheckBox) w);
	        }
	      }
	}

}
