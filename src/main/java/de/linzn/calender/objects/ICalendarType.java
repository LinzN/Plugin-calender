/*
 * Copyright (C) 2020. Niklas Linz - All Rights Reserved
 * You may use, distribute and modify this code under the
 * terms of the LGPLv3 license, which unfortunately won't be
 * written for another century.
 *
 * You should have received a copy of the LGPLv3 license with
 * this file. If not, please write to: niklas.linz@enigmar.de
 *
 */

package de.linzn.calender.objects;

import de.linzn.calender.objects.trashes.BlackTrash;
import de.linzn.calender.objects.trashes.BlueTrash;
import de.linzn.calender.objects.trashes.GreenTrash;
import de.linzn.calender.objects.trashes.YellowTrash;
import net.fortuna.ical4j.model.component.VEvent;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public interface ICalendarType {

    static ICalendarType getCalendarTypeInstance(VEvent vEvent) {
        String summary = vEvent.getSummary().getValue();
        ICalendarType iTrash;

        if (summary.equalsIgnoreCase("Restabfalltonne")) {
            iTrash = new BlackTrash(vEvent.getStartDate().getDate());
        } else if (summary.equalsIgnoreCase("Gelber Sack")) {
            iTrash = new YellowTrash(vEvent.getStartDate().getDate());
        } else if (summary.equalsIgnoreCase("Biotonne")) {
            iTrash = new GreenTrash(vEvent.getStartDate().getDate());
        } else if (summary.equalsIgnoreCase("Blaue Tonne")) {
            iTrash = new BlueTrash(vEvent.getStartDate().getDate());
        } else {
            iTrash = new OtherType(summary, vEvent.getStartDate().getDate());
        }
        return iTrash;
    }

    default String getDayName() {
        Format dateFormat = new SimpleDateFormat("EEEE", Locale.GERMANY);
        Date date = getDate();
        return dateFormat.format(date);
    }

    Date getDate();

    String getName();

    TrashType getType();
}
