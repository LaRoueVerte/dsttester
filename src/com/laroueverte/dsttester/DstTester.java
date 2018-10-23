package com.laroueverte.dsttester;

import java.text.ParseException;

import com.lrv.common.utils.date.DateBuilder;

public class DstTester {
	public static void main(String[] args) throws ParseException {
		if (args.length != 2) {
			System.out.println("Usage java -jar dsttester.jar [SPRING|AUTUMN] YYYY-MM-dd");
			System.out.println("		YYYY-MM-dd is the day of the switch (2am");
			System.exit(0);
		}

		DateBuilder date = DateBuilder.iso(args[1]);
		System.out.println("Running for " + date.toIsoTimestamp());
		if (args[0].equals("AUTUMN")) {
			date.setHour(1);
			date.setMinute(59);
			assertDate("", date, 1, 59);

			date.addMinutes(1);
			assertDate("1 minute later", date, 2, 0);

			date.addMinutes(59);
			assertDate("59 minutes later", date, 2, 59);

			date.addMinutes(1);
			assertDate("1 minute later", date, 2, 0);

			date.addMinutes(59);
			assertDate("59 minutes later", date, 2, 59);

			date.addMinutes(1);
			assertDate("1 minute later", date, 3, 0);

			date.addMinutes(1);
			assertDate("1 minute later", date, 3, 1);
		}

		if (args[0].equals("SPRING")) {
			date.setHour(1);
			date.setMinute(59);
			assertDate("", date, 1, 59);

			date.addMinutes(1);
			assertDate("1 minute later", date, 3, 0);

			date.addMinutes(1);
			assertDate("1 minute later", date, 3, 01);
		}
	}

	private static void assertDate(String message, DateBuilder date, int hour, int minute) {
		if (date.getHour() != hour) {
			throw new RuntimeException(message + ", the hour is not " + hour);
		}
		if (date.getMinute() != minute) {
			throw new RuntimeException(message + "the minute is not " + minute);
		}

		System.out.println(message + ", It is " + date.toIsoTimestamp() + " as expected");
	}
}
